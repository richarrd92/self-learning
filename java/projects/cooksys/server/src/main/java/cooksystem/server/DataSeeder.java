package cooksystem.server;

import cooksystem.server.entities.Announcement;
import cooksystem.server.entities.Company;
import cooksystem.server.entities.User;
import cooksystem.server.entities.embaddables.Credentials;
import cooksystem.server.entities.embaddables.Profile;
import cooksystem.server.entities.embaddables.UserState;
import cooksystem.server.repositories.AnnouncementRepository;
import cooksystem.server.repositories.CompanyRepository;
import cooksystem.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final AnnouncementRepository announcementRepository;

    @Override
    public void run(String... args) throws Exception {
        seedCompanies();
        seedUsers();
        seedAnnouncements();
    }

    public void seedUsers() {
        if (userRepository.count() == 0) {
            List<Company> companies = companyRepository.findAll();
            Company fedex = companies.stream().filter(c -> c.getName().equalsIgnoreCase("FedEx")).findFirst().orElseThrow();
            Company amazon = companies.stream().filter(c -> c.getName().equalsIgnoreCase("Amazon")).findFirst().orElseThrow();
            Company cookSystems = companies.stream().filter(c -> c.getName().equalsIgnoreCase("CookSystems")).findFirst().orElseThrow();
            Company uberEats = companies.stream().filter(c -> c.getName().equalsIgnoreCase("UberEats")).findFirst().orElseThrow();
            Company happySoft = companies.stream().filter(c -> c.getName().equalsIgnoreCase("Happy Soft")).findFirst().orElseThrow();

            User richard = new User();
            richard.setCredentials(new Credentials("richardmaliyetu", "password1", "richard.maliyetu@example.com"));
            richard.setProfile(new Profile("Richard", "Maliyetu", "5555555555"));
            UserState richardState = new UserState();
            richardState.setAdmin(true);
            richardState.setStatus(UserState.Status.JOINED);
            richard.setUserState(richardState);

            User orson = new User();
            orson.setCredentials(new Credentials("orsonjiang", "password2", "orson.jiang@example.com"));
            orson.setProfile(new Profile("Orson", "Jiang", "6666666666"));
            UserState orsonState = new UserState();
            orsonState.setAdmin(true);
            orsonState.setStatus(UserState.Status.JOINED);
            orson.setUserState(orsonState);

            User randy = new User();
            randy.setCredentials(new Credentials("randyli", "password3", "randy.li@example.com"));
            randy.setProfile(new Profile("Randy", "Li", "7777777777"));
            UserState randyState = new UserState();
            randyState.setStatus(UserState.Status.JOINED); // override status
            randyState.setActive(true);
            randy.setUserState(randyState);

            User alice = new User();
            alice.setCredentials(new Credentials("alicebrown", "password4", "alice.brown@example.com"));
            alice.setProfile(new Profile("Alice", "Brown", "8888888888"));
            // default userState applies (active=false, admin=false, status=PENDING)

            User sarah = new User();
            sarah.setCredentials(new Credentials("sarahchen", "password5", "sarah.chen@example.com"));
            sarah.setProfile(new Profile("Sarah", "Chen", "9999999999"));
            UserState sarahState = new UserState();
            sarahState.setActive(true);
            sarahState.setStatus(UserState.Status.JOINED);
            sarah.setUserState(sarahState);

            User michael = new User();
            michael.setCredentials(new Credentials("michaelsmith", "password6", "michael.smith@example.com"));
            michael.setProfile(new Profile("Michael", "Smith", "1010101010"));
            UserState michaelState = new UserState();
            michaelState.setActive(true);
            michaelState.setStatus(UserState.Status.JOINED);
            michael.setUserState(michaelState);

            User jennifer = new User();
            jennifer.setCredentials(new Credentials("jenniferwang", "password7", "jennifer.wang@example.com"));
            jennifer.setProfile(new Profile("Jennifer", "Wang", "1212121212"));
            UserState jenniferState = new UserState();
            jenniferState.setActive(true);
            jenniferState.setStatus(UserState.Status.JOINED);
            jennifer.setUserState(jenniferState);

            // Admins are added to ALL companies
            richard.getCompanies().add(fedex);
            richard.getCompanies().add(amazon);
            richard.getCompanies().add(happySoft);
            richard.getCompanies().add(cookSystems);
            richard.getCompanies().add(uberEats);

            orson.getCompanies().add(fedex);
            orson.getCompanies().add(amazon);
            orson.getCompanies().add(happySoft);
            orson.getCompanies().add(cookSystems);
            orson.getCompanies().add(uberEats);

            // Regular users are assigned to ONE company each
            randy.getCompanies().add(cookSystems);

            alice.getCompanies().add(fedex);

            sarah.getCompanies().add(happySoft);

            michael.getCompanies().add(amazon);

            jennifer.getCompanies().add(uberEats);

            // Save all users
            userRepository.saveAll(List.of(richard, orson, randy, alice, sarah, michael, jennifer));
        }
    }

    public void seedCompanies() {
        if (companyRepository.count() == 0) {
            Company happySoft = new Company();
            happySoft.setName("Happy Soft");
            happySoft.setDescription("Maker of happy video games");

            Company cookSystems = new Company();
            cookSystems.setName("CookSystems");
            cookSystems.setDescription("Contracts people out to clients");

            Company fedex = new Company();
            fedex.setName("FedEx");
            fedex.setDescription("Global courier delivery services company");

            Company amazon = new Company();
            amazon.setName("Amazon");
            amazon.setDescription("E-commerce and cloud computing giant");

            Company uberEats = new Company();
            uberEats.setName("UberEats");
            uberEats.setDescription("Food delivery platform");

            companyRepository.saveAll(List.of(happySoft, cookSystems, fedex, amazon, uberEats));
        }
    }

    public void seedAnnouncements() {
        if (announcementRepository.count() == 0) {
            List<Company> companies = companyRepository.findAll();
            List<User> users = userRepository.findAll();

            Company fedex = companies.stream().filter(c -> c.getName().equalsIgnoreCase("FedEx")).findFirst().orElseThrow();
            Company amazon = companies.stream().filter(c -> c.getName().equalsIgnoreCase("Amazon")).findFirst().orElseThrow();
            Company cookSystems = companies.stream().filter(c -> c.getName().equalsIgnoreCase("CookSystems")).findFirst().orElseThrow();
            Company uberEats = companies.stream().filter(c -> c.getName().equalsIgnoreCase("UberEats")).findFirst().orElseThrow();
            Company happySoft = companies.stream().filter(c -> c.getName().equalsIgnoreCase("Happy Soft")).findFirst().orElseThrow();

            User richard = users.stream().filter(u -> u.getCredentials().getUsername().equals("richardmaliyetu")).findFirst().orElseThrow();
            User orson = users.stream().filter(u -> u.getCredentials().getUsername().equals("orsonjiang")).findFirst().orElseThrow();
            User randy = users.stream().filter(u -> u.getCredentials().getUsername().equals("randyli")).findFirst().orElseThrow();
            User alice = users.stream().filter(u -> u.getCredentials().getUsername().equals("alicebrown")).findFirst().orElseThrow();
            User sarah = users.stream().filter(u -> u.getCredentials().getUsername().equals("sarahchen")).findFirst().orElseThrow();
            User michael = users.stream().filter(u -> u.getCredentials().getUsername().equals("michaelsmith")).findFirst().orElseThrow();
            User jennifer = users.stream().filter(u -> u.getCredentials().getUsername().equals("jenniferwang")).findFirst().orElseThrow();

            // FedEx Announcements
            Announcement fedex1 = new Announcement();
            fedex1.setTitle("Holiday Shipping Schedule");
            fedex1.setMessage("Please note our updated shipping schedule for the upcoming holiday season. All shipments must be processed by December 20th to guarantee delivery before Christmas.");
            fedex1.setCompany(fedex);
            fedex1.setAuthor(richard);

            Announcement fedex2 = new Announcement();
            fedex2.setTitle("Safety Training Required");
            fedex2.setMessage("All warehouse staff are required to complete the updated safety training module by the end of this month. Login credentials have been sent to your company email.");
            fedex2.setCompany(fedex);
            fedex2.setAuthor(alice);

            // Amazon Announcements
            Announcement amazon1 = new Announcement();
            amazon1.setTitle("Prime Day Preparation");
            amazon1.setMessage("Prime Day is approaching! All departments should review their inventory levels and staffing schedules. Extra shifts are available - contact your manager to sign up.");
            amazon1.setCompany(amazon);
            amazon1.setAuthor(richard);

            Announcement amazon2 = new Announcement();
            amazon2.setTitle("New Warehouse Opening");
            amazon2.setMessage("Exciting news! We're opening a new fulfillment center in Dallas, TX next quarter. Internal transfer applications are now being accepted.");
            amazon2.setCompany(amazon);
            amazon2.setAuthor(orson);

            // CookSystems Announcements
            Announcement cook1 = new Announcement();
            cook1.setTitle("Team Building Event");
            cook1.setMessage("Join us for our quarterly team building event on Friday! We'll have lunch, games, and prizes. RSVP by Wednesday to help us get an accurate headcount.");
            cook1.setCompany(cookSystems);
            cook1.setAuthor(randy);

            Announcement cook2 = new Announcement();
            cook2.setTitle("New Client Onboarding");
            cook2.setMessage("Welcome to our newest client partnership! We're excited to announce we'll be working with a Fortune 500 tech company starting next month. More details to follow.");
            cook2.setCompany(cookSystems);
            cook2.setAuthor(orson);

            // UberEats Announcements
            Announcement uber1 = new Announcement();
            uber1.setTitle("Driver Incentive Program");
            uber1.setMessage("New driver incentive program launching this week! Earn bonus pay for deliveries during peak hours. Check the app for full details and qualifying times.");
            uber1.setCompany(uberEats);
            uber1.setAuthor(orson);

            Announcement uber2 = new Announcement();
            uber2.setTitle("Restaurant Partner Expansion");
            uber2.setMessage("We've added 50+ new restaurant partners in your area! Check out the new options and help us spread the word to customers.");
            uber2.setCompany(uberEats);
            uber2.setAuthor(jennifer);

            // Happy Soft Announcements
            Announcement happy1 = new Announcement();
            happy1.setTitle("New Game Release!");
            happy1.setMessage("We're thrilled to announce that 'Happy Quest 2' has officially launched! The team has worked incredibly hard on this release. Congratulations everyone!");
            happy1.setCompany(happySoft);
            happy1.setAuthor(richard);

            Announcement happy2 = new Announcement();
            happy2.setTitle("Crunch Time Policy Update");
            happy2.setMessage("Important update: We're implementing a new work-life balance policy. Mandatory crunch time is being eliminated. All overtime must be voluntary and approved in advance.");
            happy2.setCompany(happySoft);
            happy2.setAuthor(richard);

            announcementRepository.saveAll(List.of(
                fedex1, fedex2, amazon1, amazon2, cook1, cook2,
                uber1, uber2, happy1, happy2
            ));
        }
    }
}
