select * from class
right outer join textbook
on class.class_title = textbook.textbook_title;