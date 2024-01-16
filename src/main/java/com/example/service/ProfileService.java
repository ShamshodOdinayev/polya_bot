package com.example.service;

import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.Step;
import com.example.repository.StudentRepository;
import com.example.util.MessageUtil;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final StudentRepository studentRepository;
    private final MessageUtil messageUtil;
    List<ProfileDTO> profileDTOList = new LinkedList<>();

    public ProfileService(StudentRepository studentRepository, MessageUtil messageUtil) {
        this.studentRepository = studentRepository;
        this.messageUtil = messageUtil;
    }

    public void messageCheck(Message message) {


    }

    public ProfileDTO getByChatId(Long chatId) {
        Optional<ProfileEntity> profileEntityOptional = studentRepository.findById(Math.toIntExact(chatId));
        if (profileEntityOptional.isEmpty()) {
            return null;
        } else {
            ProfileEntity profileEntity = profileEntityOptional.get();
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setChatId(profileEntity.getChatId());
            profileDTO.setRole(profileEntity.getRole());
            profileDTO.setPhone(profileEntity.getPhone());
            profileDTO.setFullName(profileEntity.getFullName());
            return profileDTO;
        }
    }

    public SendMessage save(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        ProfileEntity profileEntity = new ProfileEntity();
        defaultProfileSave(chatId);
        sendMessage.setChatId(chatId);
        sendMessage.setText(messageUtil.getMessage("onStartText"));
        return sendMessage;
    }

    private void defaultProfileSave(Long chatId) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setChatId(String.valueOf(chatId));
        profileDTO.setRole(ProfileRole.USER);
        profileDTO.setStep(Step.SIGN_UP);
        profileDTOList.add(profileDTO);
    }
}
