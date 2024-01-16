package com.example.controller;

import com.example.dto.ProfileDTO;
import com.example.service.ProfileService;
import io.github.nazarovctrl.telegrambotspring.bot.MessageSender;
import io.github.nazarovctrl.telegrambotspring.controller.AbstractUpdateController;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class UpdateController extends AbstractUpdateController {
    private final MessageSender messageSender;
    private final ProfileService profileService;

    public UpdateController(MessageSender messageSender, ProfileService profileService) {
        this.messageSender = messageSender;
        this.profileService = profileService;
    }

    @Override
    public void handle(Update update) {
        try {
            if (update.hasMessage()) {
                receiveMessage(update);
            } else if (update.hasCallbackQuery()) {

            }
        } catch (Exception e) {
            throw new RuntimeException("Exception");
        }

    }

    private void receiveMessage(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        ProfileDTO profileDTO = profileService.getByChatId(chatId);
        if (message.hasText()) {
            whenHasText(message, profileDTO);
        }

    }

    private void whenHasText(Message message, ProfileDTO profileDTO) {
        String text = message.getText();
        if (text.equals("/start")) {
            onStartText(profileDTO, message.getChatId());
        }
    }

    private void onStartText(ProfileDTO profileDTO, Long chatId) {
        if (profileDTO == null) {
            SendMessage sendMessage = profileService.save(chatId);
            try {
                messageSender.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
