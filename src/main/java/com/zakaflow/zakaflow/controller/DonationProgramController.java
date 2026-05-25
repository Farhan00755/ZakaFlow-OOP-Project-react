package com.zakaflow.zakaflow.controller;

import com.zakaflow.zakaflow.service.DonationProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/programs")
@RequiredArgsConstructor
public class DonationProgramController {

    private final DonationProgramService donationProgramService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("programs", donationProgramService.findAllActive());
        return "programs/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        var program = donationProgramService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Program tidak ditemukan"));
        if (!program.isPubliclyVisible()) {
            throw new IllegalArgumentException("Program tidak tersedia");
        }
        model.addAttribute("program", program);
        return "programs/detail";
    }
}
