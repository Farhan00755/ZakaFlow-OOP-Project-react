package com.zakaflow.zakaflow.controller;

import com.zakaflow.zakaflow.model.DonationTransaction;
import com.zakaflow.zakaflow.model.TransactionStatus;
import com.zakaflow.zakaflow.model.User;
import com.zakaflow.zakaflow.service.DonationProgramService;
import com.zakaflow.zakaflow.service.DonationTransactionService;
import com.zakaflow.zakaflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final DonationTransactionService donationTransactionService;
    private final DonationProgramService donationProgramService;

    @GetMapping
    public String profile(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Pengguna tidak ditemukan"));

        List<DonationTransaction> transactions = donationTransactionService.findByUserId(user.getId());
        BigDecimal totalDonated = transactions.stream()
                .filter(t -> t.getStatus() == TransactionStatus.SUCCESS)
                .map(DonationTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("user", user);
        model.addAttribute("transactions", transactions);
        model.addAttribute("donationCount", transactions.size());
        model.addAttribute("totalDonated", totalDonated);
        boolean isAdmin = "ADMIN".equals(user.getRole());
        model.addAttribute("isAdmin", isAdmin);
        if (isAdmin) {
            model.addAttribute("adminPrograms", donationProgramService.findAll());
        }
        return "profile/view";
    }
}
