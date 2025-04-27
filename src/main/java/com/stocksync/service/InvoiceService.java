package com.stocksync.service;

import com.stocksync.dto.InvoiceDTO;
import com.stocksync.dto.InvoiceItemDTO;
import com.stocksync.entity.Invoice;
import com.stocksync.entity.InvoiceItem;
import com.stocksync.repository.InvoiceRepository;
import com.stocksync.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Transactional
    public InvoiceDTO createInvoice(InvoiceDTO dto) {
        validateInvoiceDTO(dto, true);
        Invoice invoice = toEntity(dto);
        invoice.setTotalAmount(calculateTotal(dto.getItems()));
        invoice = invoiceRepository.save(invoice);
        saveItems(invoice, dto.getItems());
        return toDTO(invoice, invoice.getItems());
    }

    @Transactional
    public InvoiceDTO updateInvoice(UUID invoiceId, InvoiceDTO dto) {
        validateInvoiceDTO(dto, false);
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        updateEntity(invoice, dto);
        invoice.setTotalAmount(calculateTotal(dto.getItems()));
        invoice.getItems().clear();
        invoiceRepository.save(invoice);
        saveItems(invoice, dto.getItems());
        return toDTO(invoice, invoice.getItems());
    }

    @Transactional
    public void deleteInvoice(UUID invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }

    @Transactional(readOnly = true)
    public InvoiceDTO getInvoice(UUID invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        return toDTO(invoice, invoice.getItems());
    }

    @Transactional(readOnly = true)
    public List<InvoiceDTO> listInvoices() {
        return invoiceRepository.findAll().stream()
                .map(inv -> toDTO(inv, inv.getItems()))
                .collect(Collectors.toList());
    }

    private void validateInvoiceDTO(InvoiceDTO dto, boolean isCreate) {
        if (dto.getContactId() == null)
            throw new IllegalArgumentException("ContactID is required");
        if (dto.getInvoiceDate() == null)
            throw new IllegalArgumentException("InvoiceDate is required");
        if (CollectionUtils.isEmpty(dto.getItems()))
            throw new IllegalArgumentException("At least one item is required");
    }

    private void saveItems(Invoice invoice, List<InvoiceItemDTO> items) {
        invoice.getItems().clear();
        List<InvoiceItem> entityItems = items.stream().map(dto -> {
            InvoiceItem item = new InvoiceItem();
            item.setInvoice(invoice);
            item.setItemId(dto.getItemId());
            item.setItemName(dto.getItemName());
            item.setQuantity(dto.getQuantity());
            item.setUnitPrice(dto.getUnitPrice());
            return item;
        }).collect(Collectors.toList());
        invoice.getItems().addAll(entityItems);
        invoiceRepository.save(invoice);
    }

    private BigDecimal calculateTotal(List<InvoiceItemDTO> items) {
        return items.stream()
                .map(i -> i.getQuantity().multiply(i.getUnitPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Invoice toEntity(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setContactId(dto.getContactId());
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setBillingAddress(dto.getBillingAddress());
        invoice.setShippingAddress(dto.getShippingAddress());
        invoice.setStatus(dto.getStatus());
        return invoice;
    }

    private void updateEntity(Invoice invoice, InvoiceDTO dto) {
        invoice.setContactId(dto.getContactId());
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setBillingAddress(dto.getBillingAddress());
        invoice.setShippingAddress(dto.getShippingAddress());
        invoice.setStatus(dto.getStatus());
    }

    private InvoiceDTO toDTO(Invoice invoice, List<InvoiceItem> items) {
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
        dto.setItems(items.stream().map(this::toItemDTO).collect(Collectors.toList()));
        return dto;
    }

    private InvoiceItemDTO toItemDTO(InvoiceItem item) {
        InvoiceItemDTO dto = new InvoiceItemDTO();
        dto.setInvoiceItemId(item.getInvoiceItemId());
        dto.setItemId(item.getItemId());
        dto.setItemName(item.getItemName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        return dto;
    }
}
