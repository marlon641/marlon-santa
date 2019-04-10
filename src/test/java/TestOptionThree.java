import org.junit.BeforeClass;
import org.junit.Test;
import solution.Person;
import solution.Person.SEX;
import solution.SecretSanta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestOptionThree extends SecretSanta{

    static List<Person> participants = new ArrayList<Person>();

    @SuppressWarnings("Duplicates") @BeforeClass
    public static void setup(){

        Person marlon = new Person("Marlon", SEX.MALE);
        Person rachel = new Person("Rachel", SEX.FEMALE);
        Person mookie = new Person("Mookie", SEX.MALE);
        List<Person> children = new ArrayList<Person>();
        children.add(mookie);
        rachel.setSpouse(marlon);
        marlon.setSpouse(rachel);
        marlon.setChildren(children);
        rachel.setChildren(children);
        participants.add(marlon);
        participants.add(rachel);
        participants.add(mookie);
        participants.add(new Person("Jack", SEX.MALE));
        participants.add(new Person("Cocoa", SEX.FEMALE));
        participants.add(new Person("BayBay", SEX.FEMALE));
        participants.add(new Person("Lambchop", SEX.FEMALE));
        participants.add(new Person("Marky", SEX.MALE));
    }

    @Test
    public void testSenderCannotSendGiftsToFamilyMember(){
        setGameYear(2015);
        List<Person> participants2015 = new ArrayList<Person>();
        participants2015.addAll(participants);
        Map<Person, Person> secretSenderReceiver =
                getSecretSantaListButLastThreeYearsAndFamily(participants2015, participants.size());
        printSecretList(secretSenderReceiver, getGameYear());

        setGameYear(2016);
        List<Person> participants2016 = new ArrayList<Person>();
        participants2016.addAll(participants);
        secretSenderReceiver = getSecretSantaListButLastThreeYearsAndFamily(participants2016,
                participants.size());
        printSecretList(secretSenderReceiver, getGameYear());

        setGameYear(2017);
        List<Person> participants2017 = new ArrayList<Person>();
        participants2017.addAll(participants);
        secretSenderReceiver = getSecretSantaListButLastThreeYearsAndFamily(participants2017,
                participants.size());
        printSecretList(secretSenderReceiver, getGameYear());

    }

    public void printSecretList(Map<Person, Person> secretSenderReceiver, int year){
        System.out.println("Year: " + year);
        for(Map.Entry<Person, Person> entry : secretSenderReceiver.entrySet()){
            System.out.println("Sender = " + entry.getKey().getFirstName() +
                    ", Recipient = " + entry.getValue().getFirstName());
        }
    }
}
