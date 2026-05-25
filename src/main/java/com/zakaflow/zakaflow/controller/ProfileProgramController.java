package com.zakaflow.zakaflow.controller;

import com.zakaflow.zakaflow.dto.ProgramCreateForm;
import com.zakaflow.zakaflow.model.Asnaf;
import com.zakaflow.zakaflow.model.DonationProgram;
import com.zakaflow.zakaflow.model.FundingType;
import com.zakaflow.zakaflow.model.ProgramStatus;
import com.zakaflow.zakaflow.model.SpecificCategory;
import com.zakaflow.zakaflow.model.ZakatType;
import com.zakaflow.zakaflow.service.DonationProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile/programs")
@RequiredArgsConstructor
public class ProfileProgramController {

    private final DonationProgramService donationProgramService;

    @GetMapping("/new")
    public String newProgramForm(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new ProgramCreateForm());
        }
        populateFormOptions(model, false, null);
        return "profile/program-form";
    }

    @PostMapping("/new")
    public String createProgram(
            @ModelAttribute("form") ProgramCreateForm form,
            @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestParam(value = "gallery", required = false) MultipartFile[] gallery,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            var program = donationProgramService.createProgram(form, thumbnail, gallery);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Program \"" + program.getTitle() + "\" berhasil dibuat.");
            return "redirect:/profile";
        } catch (IllegalArgumentException ex) {
            return handleFormError(model, form, false, null, ex.getMessage());
        }
    }

    @GetMapping("/{id}/edit")
    public String editProgramForm(@PathVariable Long id, Model model) {
        DonationProgram program = donationProgramService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Program tidak ditemukan"));

        if (!model.containsAttribute("form")) {
            model.addAttribute("form", ProgramCreateForm.from(program));
        }
        populateFormOptions(model, true, program);
        return "profile/program-form";
    }

    @PostMapping("/{id}/edit")
    public String updateProgram(
            @PathVariable Long id,
            @ModelAttribute("form") ProgramCreateForm form,
            @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestParam(value = "gallery", required = false) MultipartFile[] gallery,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            var program = donationProgramService.updateProgram(id, form, thumbnail, gallery);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Program \"" + program.getTitle() + "\" berhasil diperbarui.");
            return "redirect:/profile";
        } catch (IllegalArgumentException ex) {
            form.setId(id);
            DonationProgram existing = donationProgramService.findById(id).orElse(null);
            return handleFormError(model, form, true, existing, ex.getMessage());
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteProgram(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            DonationProgram program = donationProgramService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Program tidak ditemukan"));
            String title = program.getTitle();
            donationProgramService.deleteProgram(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Program \"" + title + "\" berhasil dihapus.");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/profile";
    }

    private String handleFormError(Model model, ProgramCreateForm form, boolean editMode,
                                   DonationProgram program, String message) {
        model.addAttribute("errorMessage", message);
        populateFormOptions(model, editMode, program);
        return "profile/program-form";
    }

    private void populateFormOptions(Model model, boolean editMode, DonationProgram program) {
        model.addAttribute("editMode", editMode);
        model.addAttribute("fundingTypes", FundingType.values());
        model.addAttribute("specificCategories", SpecificCategory.values());
        model.addAttribute("asnafOptions", Asnaf.values());
        model.addAttribute("zakatTypes", ZakatType.values());
        model.addAttribute("programStatuses", ProgramStatus.values());
        if (editMode && program != null) {
            model.addAttribute("existingProgram", program);
        }
    }
}
