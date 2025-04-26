package com.stocksync.backend.service;

import com.stocksync.backend.dto.InvoiceDTO;
import com.stocksync.backend.dto.InvoiceItemDTO;
import com.stocksync.backend.entity.Invoice;
import com.stocksync.backend.entity.InvoiceItem;
import com.stocksync.backend.repository.InvoiceRepository;
import com.stocksync.backend.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    private InvoiceDTO toDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setInvoiceId(invoice.getInvoiceId());
        dto.setContactId(invoice.getContactId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setDueDate(invoice.getDueDate());
        dto.setBillingAddress(invoice.getBillingAddress());
        dto.setShippingAddress(invoice.getShippingAddress());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setStatus(invoice.getStatus());
        List<InvoiceItem> items = invoiceItemRepository.findByInvoiceId(invoice.getInvoiceId());
        List<InvoiceItemDTO> itemDTOs = items.stream().map(item -> {
            InvoiceItemDTO iidto = new InvoiceItemDTO();
            iidto.setInvoiceItemId(item.getInvoiceItemId());
            iidto.setItemId(item.getItemId());
            iidto.setItemName(item.getItemName());
            iidto.setQuantity(item.getQuantity());
            iidto.setUnitPrice(item.getUnitPrice());
            return iidto;
        }).collect(Collectors.toList());
        dto.setItems(itemDTOs);
        return dto;
    }

    private void updateEntity(Invoice invoice, InvoiceDTO dto) {
        invoice.setContactId(dto.getContactId());
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setBillingAddress(dto.getBillingAddress());
        invoice.setShippingAddress(dto.getShippingAddress());
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setStatus(dto.getStatus());
    }

    public InvoiceDTO createInvoice(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        updateEntity(invoice, dto);
        long now = Instant.now().toEpochMilli();
        invoice.setCreatedTime(now);
        invoice.setUpdatedTime(now);
        invoice = invoiceRepository.save(invoice);

        if (dto.getItems() != null) {
            for (InvoiceItemDTO itemDTO : dto.getItems()) {
                InvoiceItem item = new InvoiceItem();
                item.setInvoiceId(invoice.getInvoiceId());
                item.setItemId(itemDTO.getItemId());
                item.setItemName(itemDTO.getItemName());
                item.setQuantity(itemDTO.getQuantity());
                item.setUnitPrice(itemDTO.getUnitPrice());
                item.setCreatedTime(now);
                item.setUpdatedTime(now);
                invoiceItemRepository.save(item);
            }
        }
        return toDTO(invoice);
    }

    public Optional<InvoiceDTO> updateInvoice(UUID id, InvoiceDTO dto) {
        Optional<Invoice> opt = invoiceRepository.findById(id);
        if (opt.isPresent()) {
            Invoice invoice = opt.get();
            updateEntity(invoice, dto);
            invoice.setUpdatedTime(Instant.now().toEpochMilli());
            invoice = invoiceRepository.save(invoice);

            // Remove old items and add new ones
            invoiceItemRepository.deleteByInvoiceId(id);
            if (dto.getItems() != null) {
                long now = Instant.now().toEpochMilli();
                for (InvoiceItemDTO itemDTO : dto.getItems()) {
                    InvoiceItem item = new InvoiceItem();
                    item.setInvoiceId(invoice.getInvoiceId());
                    item.setItemId(itemDTO.getItemId());
                    item.setItemName(itemDTO.getItemName());
                    item.setQuantity(itemDTO.getQuantity());
                    item.setUnitPrice(itemDTO.getUnitPrice());
                    item.setCreatedTime(now);
                    item.setUpdatedTime(now);
                    invoiceItemRepository.save(item);
                }
            }
            return Optional.of(toDTO(invoice));
        }
        return Optional.empty();
    }

    public boolean deleteInvoice(UUID id) {
        if (invoiceRepository.existsById(id)) {
            invoiceItemRepository.deleteByInvoiceId(id);
            invoiceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<InvoiceDTO> getInvoice(UUID id) {
        return invoiceRepository.findById(id).map(this::toDTO);
    }

    public List<InvoiceDTO> listInvoices() {
        return invoiceRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
}
