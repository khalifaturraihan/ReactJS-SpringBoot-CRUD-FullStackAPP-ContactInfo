package com.simplecrud.crud.scheduler;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Component
public class MailingScheduler {
    @Autowired
    private JavaMailSender emailSender;
    private static Logger log = LogManager.getLogger(MailingScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dMMM_yyyy"); //20Dec_2023
    @Scheduled(cron = "0 */2 * * * *") //s m h date month day, (0 59 1 * * *) scheduled every day at 1:59
    public void sendEmailWithAttachment() throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        log.info("Mail have succesed at ", dateFormat.format(new Date()));

        helper.setTo("khalifatur.raihan@asyst.co.id"); //email set to
        helper.setSubject("Laporan Harian " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        helper.setText("Berikut adalah laporan harian terlampir.");
        // Ganti path file dengan path file laporan Anda
        getFileByModified("src/main/resources"); //File Path Excell Report
        FileSystemResource file
                = new FileSystemResource(getFileByName("src/main/resources")); //file path excell
        if (file.exists()) {
            helper.setText("Berikut adalah laporan harian terlampir.");
            helper.addAttachment(file.getFilename(), file);
            emailSender.send(message);
        } else {
            log.info("The time is now {}", dateFormat.format(new Date()));
        }
    }
    public static File getFileByModified(String directoryFilePath) //get File last modified
    {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null)
        {
            for (File file : files)
            {
                if (file.lastModified() > lastModifiedTime)
                {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        return chosenFile;
    }
    public static File getFileByName(String directoryFilePath) {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        String date = dateFormat.format(new Date());
        File chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(date + "_bags.xlsx")) {
                    chosenFile = file;
                    break; // Keluar dari loop setelah file ditemukan
                }
            }
        }

        if (chosenFile != null) {
            log.info("File with name {} found.", chosenFile.getName());
        } else {
            log.warn("File with name {} not found.", date + "_bags.xlsx");
        }

        return chosenFile;
    }
}