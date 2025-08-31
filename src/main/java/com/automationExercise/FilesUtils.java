package com.automationExercise;

import com.automationExercise.utils.dataReader.PropertyReader;
import com.automationExercise.utils.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static org.apache.poi.openxml4j.opc.internal.FileHelper.copyFile;

public class FilesUtils {
private static final String USER_DIR=PropertyReader.getProperty("user.dir")+File.separator;
    //Renaming
    public static void renameFile(String oldName, String newName) {
        try {
            var targetFile = new File(oldName);
            String targetDirectory = targetFile.getParentFile().getAbsolutePath();
            File newFile = new File(targetDirectory + File.separator + newName);
            if (!targetFile.getPath().equals(newFile.getPath())) {
                copyFile(targetFile, newFile);
                org.apache.commons.io.FileUtils.deleteQuietly(targetFile);
                LogsManager.info("Target File Path: \"" + oldName + "\", file was renamed to \"" + newName + "\".");
            } else {
                LogsManager.info(("Target File Path: \"" + oldName + "\", already has the desired name \"" + newName + "\"."));
            }
        } catch (IOException e) {
            LogsManager.error(e.getMessage());
        }
    }
    //Creating Directory

    public static void createDirectory(String path) {
        try {
          File file=new File(USER_DIR+path);
          if (!file.exists()){
                  file.mkdirs();
                  LogsManager.info("Directory created successfully at " + path);
              }
        } catch (Exception e) {
            LogsManager.error("Error creating directory " + path, e.getMessage());
        }
    }
    //force delete
    public static void forceDelete(File file) {
        try {
            org.apache.commons.io.FileUtils.forceDeleteOnExit(file);
            LogsManager.info("File deleted: " + file.getAbsolutePath());
        } catch (IOException e) {
            LogsManager.error("Failed to delete file: " + file.getAbsolutePath(), e.getMessage());
        }
    }
    //Cleaning Directory

    public static void cleanDirectory(File file) {
        try {
            FileUtils.deleteQuietly(file);
        } catch (Exception exception) {
            LogsManager.error("Error cleaning directory"+ file.getAbsolutePath(), exception.getMessage());
        }
    }

    public static void main(String[] args) {
        String oldFileName = "oldFile.txt";
        String newFileName = "newFile.txt";
        renameFile(oldFileName, newFileName);
    }
    //check if the file exists
    public static boolean isFileExists( String fileName) {
        String filePath = USER_DIR + "/src/test/resources/downloads/" ;
        File file = new File(filePath+ fileName);
        return file.exists();
    }
}
