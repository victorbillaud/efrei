Sure, I can help you with that. Here's an implementation of the CourseManagement application using SOLID principles in Java.

Exercise 1 - SRP (Single Responsibility Principle):

```java
public class Course {
    private String name;
    private int distance;
    private int duration;
    private List<Participant> participants;

    public Course(String name, int distance, int duration) {
        this.name = name;
        this.distance = distance;
        this.duration = duration;
        this.participants = new ArrayList<>();
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }
}

public class Participant {
    private String name;
    private String firstName;
    private int age;

    public Participant(String name, String firstName, int age) {
        this.name = name;
        this.firstName = firstName;
        this.age = age;
    }
}
```

Exercise 2 - OCP (Open-Closed Principle):

```java
public abstract class CourseType {
    public abstract int calculateDistance();

    public abstract int calculateDuration();
}

public class MarathonCourse extends CourseType {
    @Override
    public int calculateDistance() {
        return 42; // Distance in kilometers
    }

    @Override
    public int calculateDuration() {
        return 180; // Duration in minutes
    }
}

// Implement other course types like HalfMarathonCourse, TenKmCourse, etc.
```

Exercise 3 - LSP (Liskov Substitution Principle):

```java
public abstract class Participant {
    private String name;
    private String firstName;
    private int age;

    public Participant(String name, String firstName, int age) {
        this.name = name;
        this.firstName = firstName;
        this.age = age;
    }

    public abstract void participate();
}

public class Runner extends Participant {
    public Runner(String name, String firstName, int age) {
        super(name, firstName, age);
    }

    @Override
    public void participate() {
        // Implementation for a runner
    }
}

// Implement other participant types like Cyclist, Walker, etc.
```

Exercise 4 - ISP (Interface Segregation Principle):

```java
public interface ParticipantManagement {
    void addParticipant(Participant participant);
    void removeParticipant(Participant participant);
    Participant findParticipant(String name);
}

public class ParticipantService implements ParticipantManagement {
    private ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public void addParticipant(Participant participant) {
        participantRepository.add(participant);
    }

    @Override
    public void removeParticipant(Participant participant) {
        participantRepository.remove(participant);
    }

    @Override
    public Participant findParticipant(String name) {
        return participantRepository.find(name);
    }
}

public class ParticipantRepository {
    public void add(Participant participant) {
        // Add participant to repository
    }

    public void remove(Participant participant) {
        // Remove participant from repository
    }

    public Participant find(String name) {
        // Find participant in repository
        return null;
    }
}
```

Exercise 5 - DIP (Dependency Inversion Principle):

```java
public interface CourseRepository {
    void save(Course course);
    Course retrieve(String name);
}

public class DatabaseCourseRepository implements CourseRepository {
    @Override
    public void save(Course course) {
        // Save course to database
    }

    @Override
    public Course retrieve(String name) {
        // Retrieve course from database
        return null;
    }
}

public class

 Course {
    private String name;
    private int distance;
    private int duration;
    private List<Participant> participants;
    private CourseRepository courseRepository;

    public Course(String name, int distance, int duration, CourseRepository courseRepository) {
        this.name = name;
        this.distance = distance;
        this.duration = duration;
        this.participants = new ArrayList<>();
        this.courseRepository = courseRepository;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

    public void save() {
        courseRepository.save(this);
    }

    public void retrieve(String name) {
        courseRepository.retrieve(name);
    }
}
```

This implementation follows the SOLID principles, with the Course class adhering to the Single Responsibility Principle (SRP), the CourseType class enabling the Open-Closed Principle (OCP), the Participant class hierarchy adhering to the Liskov Substitution Principle (LSP), the ParticipantService interface following the Interface Segregation Principle (ISP), and the Course class using the CourseRepository interface, thereby applying the Dependency Inversion Principle (DIP).

Please note that this is a simplified implementation to demonstrate the principles. In a real-world scenario, you may need to add additional methods, error handling, and implement the repository or database operations properly.