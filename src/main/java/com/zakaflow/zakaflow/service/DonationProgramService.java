package com.zakaflow.zakaflow.service;

import com.zakaflow.zakaflow.dto.ProgramCreateForm;
import com.zakaflow.zakaflow.model.DonationProgram;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DonationProgramService {

    List<DonationProgram> findAll();

    List<DonationProgram> findAllActive();

    Optional<DonationProgram> findById(Long id);

    DonationProgram save(DonationProgram program);

    DonationProgram createProgram(ProgramCreateForm form, MultipartFile thumbnail, MultipartFile[] gallery);

    DonationProgram updateProgram(Long id, ProgramCreateForm form, MultipartFile thumbnail, MultipartFile[] gallery);

    void deleteProgram(Long id);
}
