package com.stocksync.service;

import com.stocksync.dto.BillDTO;
import com.stocksync.dto.BillItemDTO;
import com.stocksync.entity.Bill;
import com.stocksync.entity.BillItem;
import com.stocksync.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    @Transactional
    public Bill create(BillDTO dto) {
        Bill bill = new Bill();
        bill.setContactId(dto.getContactId());
        bill.setBillNumber(dto.getBillNumber());
        bill.setBillDate(dto.getBillDate());
        bill.setDueDate(dto.getDueDate());
        bill.setBillingAddress(dto.getBillingAddress());
        bill.setStatus(dto.getStatus());

        double total = 0.0;
        List<BillItem> items = new ArrayList<>();
        for (BillItemDTO itemDTO : dto.getItems()) {
            BillItem item = new BillItem();
            item.setBill(bill);
            item.setItemId(itemDTO.getItemId());
            item.setItemName(itemDTO.getItemName());
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(itemDTO.getUnitPrice());
            items.add(item);
            total += itemDTO.getQuantity() * itemDTO.getUnitPrice();
        }
        bill.setItems(items);
        bill.setTotalAmount(total);
        return billRepository.save(bill);
    }

    @Transactional
    public Optional<Bill> update(UUID id, BillDTO dto) {
        return billRepository.findById(id).map(bill -> {
            bill.setContactId(dto.getContactId());
            bill.setBillNumber(dto.getBillNumber());
            bill.setBillDate(dto.getBillDate());
            bill.setDueDate(dto.getDueDate());
            bill.setBillingAddress(dto.getBillingAddress());
            bill.setStatus(dto.getStatus());

            bill.getItems().clear();
            double total = 0.0;
            for (BillItemDTO itemDTO : dto.getItems()) {
                BillItem item = new BillItem();
                item.setBill(bill);
                item.setItemId(itemDTO.getItemId());
                item.setItemName(itemDTO.getItemName());
                item.setQuantity(itemDTO.getQuantity());
                item.setUnitPrice(itemDTO.getUnitPrice());
                bill.getItems().add(item);
                total += itemDTO.getQuantity() * itemDTO.getUnitPrice();
            }
            bill.setTotalAmount(total);
            return billRepository.save(bill);
        });
    }

    @Transactional
    public boolean delete(UUID id) {
        if (billRepository.existsById(id)) {
            billRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Bill> get(UUID id) {
        return billRepository.findById(id);
    }

    public List<Bill> getAll() {
        return billRepository.findAll();
    }
}
