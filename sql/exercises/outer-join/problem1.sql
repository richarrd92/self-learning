select * from class
left outer join student
on class.class_title = student.class_title;