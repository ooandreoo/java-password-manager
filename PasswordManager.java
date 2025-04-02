import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

class PasswordManager {
    // research if it is possible to create user for app
    // in order to allow only permission for rw and creating files
    // in appData folder
    private static final int PASSPHRASE_LENGTH = 8;
    private static Scanner userInput = new Scanner(System.in);
    private char[][] dictionary;

    private static void verifyEncryptionKeyExistence() {
        File userKeyFile = new File("./appData/userkey.txt");
        if(!userKeyFile.exists()){
            generateKey(userKeyFile);
        }
    }

    // We will use this for password validation later too
    private static boolean validateString(int length, char[][] dictionary, String passphrase) {
        // check length
        boolean validLength = passphrase.length() == length;
        // check passphrase contains at least 1 element of dictionary
        boolean validAccordingToDictionary = true;

        for(int i=0; i<dictionary.length; i++) {
            boolean validRule = false;
            for(int j=0; j<dictionary[i].length; j++) {
                if(passphrase.contains(String.valueOf(dictionary[i][j]))){
                    validRule = true;
                    break;
                }
            }
            if(!validRule){
                validAccordingToDictionary = false;
                break;
            }
        }
        return validLength && validAccordingToDictionary;
    }

    private static boolean validateKey(String key) {
        // we will ask for a 8 lenght passphrase, containing lowercase uppercase digits and special characters
        //return validateString(PASSPHRASE_LENGTH, COMPLETE_HERE, key);
        return true;
    }

    private static boolean validatePairMatch(String key, String validationKey) {
        return key.equals(validationKey);
    }

    private static String getValidKeyFromUser() {
        boolean validKey = false;
        boolean keyPairMatch = false;
        String userKey;
        System.out.println("There is no userkey registered");
        do {
            System.out.print("Please create a valid key. Write a 8 character key: ");
            String key = userInput.nextLine();
            key = key.replaceAll(" ", "");
            //validKey = validateKey(key);
            validKey = true;
            System.out.println("");
            System.out.print("Please insert again the same key: ");
            String validationKey = userInput.nextLine();
            System.out.println("");
            validationKey = validationKey.replaceAll(" ","");
            keyPairMatch = validatePairMatch(key, validationKey);
            userKey = key;
        } while (!validKey || !keyPairMatch);
        return userKey;
    }

    private static void generateKey(File userKeyFile) {
        // we should only store a salted hash of this, to validate
        // each time the user provides its passphrase
        String key = getValidKeyFromUser();
        try{
            userKeyFile.createNewFile();
            FileWriter userKeyFileWriter = new FileWriter("./appData/userkey.txt");
            userKeyFileWriter.write(key);
            userKeyFileWriter.close();
        } catch (IOException e) {
            System.out.println("an error while trying to write a file occurred");
        }
    }

    private static void listenForUserRequest() {
        // ask if password is to be retrieved or created
        // if retrieved then ask for account and encryption key to get it
        // if created then
        // read config file to get all constants
        // ask for account type "git, fb, etc"
        // show current parameters and ask if they are to be used
        // or if customization is needed
        // after that proceed to generate the password
        // once the password is generated, encrypt it with the user created key
        return;
    }

    private static boolean checkAppFilesAreOkay() {
        // here we will check the files that are used by this app
        // it will only run if there's no file missing
        
        return true;
    }

    public static void main(String[] args) {
        if(checkAppFilesAreOkay()) {
            verifyEncryptionKeyExistence();
            listenForUserRequest();
        }
    }
}
