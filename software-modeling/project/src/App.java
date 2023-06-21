import catalog.Course;
import catalog.SubTopic;
import catalog.Theme;
import catalog.Topic;

public class App {
    public static void main(String[] args) throws Exception {
        // Create Courses
        Course course1 = new Course("Course 1");
        Course course2 = new Course("Course 2");
        Course course3 = new Course("Course 3");
        Course course4 = new Course("Course 4");

        // Create SubTopics and add courses
        SubTopic subTopic1 = new SubTopic("SubTopic 1");
        subTopic1.add(course1);
        subTopic1.add(course2);

        SubTopic subTopic2 = new SubTopic("SubTopic 2");
        subTopic2.add(course3);
        subTopic2.add(course4);

        // Create Topic and add subtopics
        Topic topic1 = new Topic("Topic 1");
        topic1.add(subTopic1);
        topic1.add(subTopic2);

        // Create Theme and add topic
        Theme theme1 = new Theme("Theme 1");
        theme1.add(topic1);

        // Display the hierarchy
        theme1.display();
    }
}
