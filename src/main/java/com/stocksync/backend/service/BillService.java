package com.stocksync.backend.service;

import com.stocksync.backend.dto.BillDTO;
import com.stocksync.backend.dto.BillItemDTO;
import com.stocksync.backend.entity.Bill;
import com.stocksync.backend.entity.BillItem;
import com.stocksync.backend.repository.BillRepository;
import com.stocksync.backend.repository.BillItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillItemRepository billItemRepository;

    private BillDTO toDTO(Bill bill) {
        BillDTO dto = new BillDTO();
        dto.setBillId(bill.getBillId());
        dto.setContactId(bill.getContactId());
        dto.setBillNumber(bill.getBillNumber());
        dto.setBillDate(bill.getBillDate());
        dto.setDueDate(bill.getDueDate());
        dto.setBillingAddress(bill.getBillingAddress());
        dto.setTotalAmount(bill.getTotalAmount());
        dto.setStatus(bill.getStatus());
        List<BillItem> items = billItemRepository.findByBillId(bill.getBillId());
        List<BillItemDTO> itemDTOs = items.stream().map(item -> {
            BillItemDTO bidto = new BillItemDTO();
            bidto.setBillItemId(item.getBillItemId());
            bidto.setItemId(item.getItemId());
            bidto.setItemName(item.getItemName());
            bidto.setQuantity(item.getQuantity());
            bidto.setUnitPrice(item.getUnitPrice());
            return bidto;
        }).collect(Collectors.toList());
        dto.setItems(itemDTOs);
        return dto;
    }

    private void updateEntity(Bill bill, BillDTO dto) {
        bill.setContactId(dto.getContactId());
        bill.setBillNumber(dto.getBillNumber());
        bill.setBillDate(dto.getBillDate());
        bill.setDueDate(dto.getDueDate());
        bill.setBillingAddress(dto.getBillingAddress());
        bill.setStatus(dto.getStatus());
    }

    private double calculateTotalAmount(List<BillItemDTO> items) {
        if (items == null) return 0.0;
        return items.stream()
                .mapToDouble(i -> (i.getQuantity() != null ? i.getQuantity() : 0.0) * (i.getUnitPrice() != null ? i.getUnitPrice() : 0.0))
                .sum();
    }

    public BillDTO createBill(BillDTO dto) {
        Bill bill = new Bill();
        updateEntity(bill, dto);
        long now = Instant.now().toEpochMilli();
        bill.setCreatedTime(now);
        bill.setUpdatedTime(now);
        bill.setTotalAmount(calculateTotalAmount(dto.getItems()));
        bill = billRepository.save(bill);

        if (dto.getItems() != null) {
            for (BillItemDTO itemDTO : dto.getItems()) {
                BillItem item = new BillItem();
                item.setBillId(bill.getBillId());
                item.setItemId(itemDTO.getItemId());
                item.setItemName(itemDTO.getItemName());
                item.setQuantity(itemDTO.getQuantity());
                item.setUnitPrice(itemDTO.getUnitPrice());
                item.setCreatedTime(now);
                item.setUpdatedTime(now);
                billItemRepository.save(item);
            }
        }
        return toDTO(bill);
    }

    public Optional<BillDTO> updateBill(UUID id, BillDTO dto) {
        Optional<Bill> opt = billRepository.findById(id);
        if (opt.isPresent()) {
            Bill bill = opt.get();
            updateEntity(bill, dto);
            bill.setUpdatedTime(Instant.now().toEpochMilli());
            bill.setTotalAmount(calculateTotalAmount(dto.getItems()));
            bill = billRepository.save(bill);

            billItemRepository.deleteByBillId(id);
            if (dto.getItems() != null) {
                long now = Instant.now().toEpochMilli();
                for (BillItemDTO itemDTO : dto.getItems()) {
                    BillItem item = new BillItem();
                    item.setBillId(bill.getBillId());
                    item.setItemId(itemDTO.getItemId());
                    item.setItemName(itemDTO.getItemName());
                    item.setQuantity(itemDTO.getQuantity());
                    item.setUnitPrice(itemDTO.getUnitPrice());
                    item.setCreatedTime(now);
                    item.setUpdatedTime(now);
                    billItemRepository.save(item);
                }
            }
            return Optional.of(toDTO(bill));
        }
        return Optional.empty();
    }

    public boolean deleteBill(UUID id) {
        if (billRepository.existsById(id)) {
            billItemRepository.deleteByBillId(id);
            billRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<BillDTO> getBill(UUID id) {
        return billRepository.findById(id).map(this::toDTO);
    }

    public List<BillDTO> listBills() {
        return billRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
}
