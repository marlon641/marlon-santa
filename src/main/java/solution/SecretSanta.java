package solution;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Secret Santa has three different methods,
 * 1. getSecretSantaList() Shuffle participants list and make sure that any participant is not sending gift to self
 */
public class SecretSanta {

    private int gameYear;
    Random random = new Random();
    // After assigning sender and receiver, add it to Map for later user, mostly print or validate in test case
    Map<Person, Person> secretSenderReceiver;
    // Sender and Recipient will be type Person and share same object properties
    Person sender;
    Person recipient;

    /**
     * Get a list of participants, and assign each person a Sender and Recipient
     * Conditions:
     * - Participant cannot send a gift to self, Sender and Recipient cannot be the same
     * @param participants List of Person participating in secret santa
     * @return Map of Sender and Recipient
     * */
    public Map<Person, Person> getSecretSantaList(List<Person> participants){
        // Create a new HashMap<Person, Person>() to reset sender, recipient Map
        secretSenderReceiver = new HashMap<Person, Person>();
        // Get the total number of participants count
        int totalParticipants = participants.size();
        validateParticipants(participants);

        // Continue participant shuffle and assign sender -> recipient, until Secret Sender Receiver map size is not
        // equal to total number of participants
        while(secretSenderReceiver.size() < totalParticipants){
            // Generate random number from size of participants
            // Get random participant from a list and assign it as sender
            sender = participants.get(random.nextInt(participants.size()));
            // Generate random number from size of participants
            // Get random participant from a list and assign it as recipient
            recipient = participants.get(random.nextInt(participants.size()));

            // Check if Sender is not Equal to Recipient, otherwise, continue getting new Sender and Recipient
            if(!sender.equals(recipient)){
                // check if recipient is assigned as a sender and recipient's recipient is not Equal to current
                // Sender, otherwise continue searching for new Sender and Recipient
                if(recipient.getRecipient() != null && recipient.getRecipient().equals(sender)){
                    continue;
                }
                // If sender doesn't have recipient and Recipient doesn't have Sender, assign sender -> recipient and
                // recipient -> sender
                if(sender.getRecipient() == null && recipient.getSender() == null) {
                    sender.setRecipient(recipient);
                    recipient.setSender(sender);
                    // Add Sender and Recipient to secretSenderReceiver map, where Sender will be key and Recipient
                    // will be value
                    secretSenderReceiver.put(sender, recipient);
                }
            }
        }
        return secretSenderReceiver;
    }




    /**
     * Get a list of participants, and assign each person a Sender and Recipient
     * Conditions:
     * - Participant cannot send a gift to self, Sender and Recipient cannot be the same
     * - Recipient cannot receive gift from same Sender in last 3 years
     * @param participants List of Person participating in secret santa
     * @param totalParticipants Total number of participants
     * @return Map of Sender and Recipient
     * */
    @SuppressWarnings("Duplicates")
    public Map<Person, Person> getSecretSantaListButLastThreeYears(List<Person> participants, int totalParticipants){
        secretSenderReceiver = new HashMap<Person, Person>();
        validateParticipants(participants);
        while(secretSenderReceiver.size() < totalParticipants){
            sender = participants.get(random.nextInt(participants.size()));
            recipient = participants.get(random.nextInt(participants.size()));

            // Get Sender and Recipient for current game year
            // Continue searching for new Sender and if Sender has given gift to Recipient in last 3 years
            if(!sender.equals(recipient) && !sender.getLastThreeYearsRecipients(this.gameYear).contains(recipient)){
                if(recipient.getRecipient(this.gameYear) != null && recipient.getRecipient(this.gameYear).equals(sender)){
                    continue;
                }
                if(sender.getRecipient(this.gameYear) == null && recipient.getSender(this.gameYear) == null) {
                    sender.setRecipient(recipient);
                    recipient.setSender(sender);
                    sender.setRecipientHistory(this.gameYear, recipient);
                    recipient.setSenderHistory(this.gameYear, sender);
                    secretSenderReceiver.put(sender, recipient);
                }
            }
        }
        return secretSenderReceiver;
    }

    /**
     * Get a list of participants, and assign each person a Sender and Recipient
     * Conditions:
     * - Participant cannot send a gift to self, Sender and Recipient cannot be the same
     * - Recipient cannot receive gift from same Sender in last 3 years
     * - Particpant cannot send a gift to immediate family (Father, Mother, Spouse and Child)
     * @param participants List of Person participating in secret santa
     * @param totalParticipants Total number of participants
     * @return Map of Sender and Recipient
     * */
    @SuppressWarnings("Duplicates")
    public Map<Person, Person> getSecretSantaListButLastThreeYearsAndFamily(List<Person> participants, int totalParticipants){
        secretSenderReceiver = new HashMap<Person, Person>();
        while(secretSenderReceiver.size() < totalParticipants){
            sender = participants.get(random.nextInt(participants.size()));
            recipient = participants.get(random.nextInt(participants.size()));

            // Get Sender and Recipient for current game year
            // Continue searching for new Sender and if Sender has given gift to Recipient in last 3 years
            // Sender and Recipient is not immediate family
            if(!sender.equals(recipient) &&
                    !sender.getLastThreeYearsRecipients(this.gameYear).contains(recipient) &&
                    !sender.isFamilyMember(recipient)){
                if(recipient.getRecipient(this.gameYear) != null && recipient.getRecipient(this.gameYear).equals(sender)){
                    continue;
                }
                if(sender.getRecipient(this.gameYear) == null && recipient.getSender(this.gameYear) == null) {
                    sender.setRecipient(recipient);
                    recipient.setSender(sender);
                    sender.setRecipientHistory(this.gameYear, recipient);
                    recipient.setSenderHistory(this.gameYear, sender);
                    secretSenderReceiver.put(sender, recipient);
                }
            }
        }

        return secretSenderReceiver;
    }

    /**
     * Get current game year
     * */
    public int getGameYear() {
        return gameYear;
    }

    /**
     * Set current game year
     * @param gameYear Current game year
     * */
    public void setGameYear(int gameYear) {
        this.gameYear = gameYear;
    }

    /**
     * Throw and exception if less than 2 participants
     * */
    public void validateParticipants(List<Person> participants){
        if (participants.size() <= 1){
            throw new UnsupportedOperationException("You need atleast 2 paticipants to play this game");
        }
    }
}

