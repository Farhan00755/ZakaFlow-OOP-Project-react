package com.zakaflow.zakaflow.dto;

import com.zakaflow.zakaflow.model.Asnaf;
import com.zakaflow.zakaflow.model.DonationProgram;
import com.zakaflow.zakaflow.model.FundingType;
import com.zakaflow.zakaflow.model.ProgramStatus;
import com.zakaflow.zakaflow.model.SpecificCategory;
import com.zakaflow.zakaflow.model.ZakatType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProgramCreateForm {

    private Long id;

    private String title;
    private String excerpt;
    private String fullDescription;
    private String beneficiary;

    private FundingType fundingType;
    private SpecificCategory specificCategory;
    private List<Asnaf> asnaf = new ArrayList<>();
    private ZakatType zakatType;

    private BigDecimal targetAmount;
    private boolean openEnded;
    private LocalDate startDate;
    private LocalDate endDate;

    private String videoUrl;

    private BigDecimal minDonationAmount;
    private String quickAmounts;
    private ProgramStatus status = ProgramStatus.DRAFT;
    private String personInCharge;

    public static ProgramCreateForm from(DonationProgram program) {
        ProgramCreateForm form = new ProgramCreateForm();
        form.setId(program.getId());
        form.setTitle(program.getTitle());
        form.setExcerpt(program.getExcerpt());
        form.setFullDescription(program.getDescription());
        form.setBeneficiary(program.getBeneficiary());
        form.setFundingType(program.getFundingType());
        form.setSpecificCategory(program.getSpecificCategory());
        form.setAsnaf(new ArrayList<>(program.getAsnafList()));
        form.setZakatType(program.getZakatType());
        form.setTargetAmount(program.getTargetAmount());
        form.setOpenEnded(program.isOpenEnded());
        form.setStartDate(program.getStartDate());
        form.setEndDate(program.getEndDate());
        form.setVideoUrl(program.getVideoUrl());
        form.setMinDonationAmount(program.getMinDonationAmount());
        form.setQuickAmounts(program.getQuickAmounts());
        form.setStatus(program.getStatus());
        form.setPersonInCharge(program.getPersonInCharge());
        return form;
    }
}
