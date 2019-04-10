package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {

    /**
     * Enum Person's Gender
     */
    public enum SEX{
        MALE,
        FEMALE;
    }

    private String firstName;
    private SEX sex;
    private Person father;
    private Person mother;
    private Person spouse;
    private List<Person> children;

    private Person recipient;
    private Person sender;

    private Map<Integer,Person> recipientHistory = new HashMap<Integer, Person>();
    private Map<Integer,Person> senderHistory = new HashMap<Integer, Person>();

    /**
     * Constructor
     * Create new participant with first name and sex
     */
    public Person(String firstName, SEX sex){
        setFirstName(firstName);
        setSex(sex);
    }

    /**
     * Get participant's first name
     * @return participant's first name
     * */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set participant's first name
     * @param firstName participant's first name
     * */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set participant's gender
     * @param sex Gender enum
     * */
    public void setSex(SEX sex) {
        this.sex = sex;
    }

    /**
     * GSt participant's spouse
     * */
    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    /**
     * Set participant's list of children
     * @param children List of child
     * */
    public void setChildren(List<Person> children) {
        this.children = children;
    }

    /**
     * Get Recipient added for this person
     * @return Recipient person object
     * */
    public Person getRecipient() {
        return recipient;
    }

    /**
     * Get Recipient for specific year of the game
     * @param gameYear Year when this Person participated in Secret Santa game
     * @return Recipient person object
     * */
    public Person getRecipient(int gameYear) {
        return this.recipientHistory.get(gameYear);
    }

    /**
     * Set Recipient for this person
     * @param recipient Recipient person object
     * */
    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    /**
     * Get Sender added for this person
     * @return Sender person object
     * */
    public Person getSender() {
        return sender;
    }

    /**
     * Get Sender for specific year of the game
     * @param gameYear Year when this Person participated in Secret Santa game
     * @return Sender person object
     * */
    public Person getSender(int gameYear) {
        return this.senderHistory.get(gameYear);
    }

    /**
     * Set Sender for this person
     * @param sender Recipient person object
     * */
    public void setSender(Person sender) {
        this.sender = sender;
    }

    public boolean isSpouse(Person spouse){
        if(this.spouse != null) {
            return this.spouse.equals(spouse);
        }
        return false;
    }

    public boolean isChild(Person child){
        if(this.children != null) {
            return this.children.contains(child);
        }
        return false;
    }

    public boolean isFather(Person father){
        if(this.father != null){
            return this.father.equals(father);
        }
        return false;
    }

    public boolean isMother(Person mother){
        if(this.father != null){
            return this.mother.equals(mother);
        }
        return false;
    }

    /**
     * Check if given participant is a family member of this person
     * Family members could be "Father", "Mother", "Spouse" and "Child"
     * @param participant Participant person object
     * @return boolean true / false
     * */
    public boolean isFamilyMember(Person participant){
        return this.isChild(participant) ||
                this.isSpouse(participant) ||
                this.isFather(participant) ||
                this.isMother(participant);
    }

    /**
     * Set recipient's history for each year
     * @param year Game year
     * @param recipient Recipient person object
     * */
    public void setRecipientHistory(int year, Person recipient) {
        this.recipientHistory.put(year, recipient);
    }

    /**
     * Set sender's history for each year
     * @param year Game year
     * @param sender Sender object
     * */
    public void setSenderHistory(int year, Person sender){
        this.senderHistory.put(year, sender);
    }

    /**
     * Return list of last three years recipient for this Person
     * @param gameYear Game year
     * @return List of recipients for this person
     * */
    public List<Person> getLastThreeYearsRecipients(int gameYear){
        List<Person> recipients = new ArrayList<Person>();
        if(this.recipientHistory == null){
            return recipients;
        }
        // Get the map of recipients history and if game year is in last last three years, add it to recipients list
        for(Map.Entry<Integer, Person> entry : this.recipientHistory.entrySet()){
            if (entry.getKey() < gameYear || entry.getKey() > gameYear - 3){
                recipients.add(entry.getValue());
            }
        }
        return recipients;
    }
}
