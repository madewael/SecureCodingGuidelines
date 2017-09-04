# Secure Coding Guidelines


The content of this course is simply *The Set of Secure Coding Guidelines* by Oracle for Java SE. These guidelines are a collection of best-practices, do's and dont's. The goal of this course is to **understand**, **recognise**, and **apply** each and all of these guidelines.


This repo is the study material for that course . All the text and information in the base version of this repo is simply a conversion from the [Oracle website](http://www.oracle.com/technetwork/java/seccodeguide-139067.html) to a Git-repo.
Every occurrence of the original text will be annotated with tho following badge: ![Author](https://img.shields.io/badge/Author-Oracle-blue.svg)

The idea of this course is to collaborate and extend this work with own examples, clarifications, and comment. You can read ```How to Contribute``` below. By contributing, you should get a better understanding of the secure coding guidelines. You can read what the ```Exam``` will look like below.

The core material can be found here: [The Guidelines](guidelines/README.md)
 

## How to Contribute

There are 9 categories of guidelines, each of them consist of some actual guidelines. For each category we created a separate directory with a README to the different guidelines which themselves also have a separate directory. In these directories you will already find a README file with the original [Oracle](http://www.oracle.com/technetwork/java/seccodeguide-139067.html) content.

It is our job to augment this texts with (running) examples and comments.
Comments can be added straight to the README. Examples are supposed to be added to the repo as executable code. To do so, create a new directory in the directory of the applicable guideline. Then, you should also add a section the original README describing what you wrote and what is shows.

**This repo is shared with all your colleagues. Do not use fowl language, do not attack or comment on existing text directly, and keep this a *safe* place to learn and study. Failing to behave correctly may result in the exclusion of write or even reading rights!**

### Adding a Section

You can always add comments and descriptions of examples simply by inserting text into README file. I order to add a section header, prepend your tile with up six hash signs ```#```.

```
	# Header 1
	  ## Header 2
	    ### Header 3
	...
```

#### Showing you agree/disagree
Add and edit the following fragment to each README file you create.

```
![Author](https://img.shields.io/badge/Author-Mattias.De.Wael-blue.svg)
![Date](https://img.shields.io/badge/20170904-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)
```

This fragments yields the following badges:

![Author](https://img.shields.io/badge/Author-Mattias.De.Wael-blue.svg)
![Date](https://img.shields.io/badge/Date-20170904-lightgrey.svg)
![Agree](https://img.shields.io/badge/AGREE-0-green.svg)
![Disagree](https://img.shields.io/badge/DISAGREE-0-red.svg)

Everyone who **creates** a new piece of text, is supposed to *update* the
content and add his username as author, and set the creation data in the ```YYYYMMDD```-format.
When you, as the original author update a piece of text *significantly*,
you should update the data-badge, and reset the agree/disagree-values.

Everyone who **reads** a piece of text, is supposed to *increment* the number in one of the last two URLs in order to show whether you agree or disagree with the text/example. Please be fair and assign only one *token*!

If you **disagree** and you think you can improve the text/example you can start an (email) conversation with the original author.
You can pro-actively correct typo's of other **small** mistakes, but you **cannot** change the original content without the consent of the original author!
Alternatively, you can add a new section yourself, and try to make **your argument** or try to explain **your vision**. You cannot, however, in anyway comment on existing text in another way than updating the numbers in the badges.

If you suspect any misuse or misconduct, you can always contact ```mattias.de.wael@howest.be``` and ask for moderation.


## Exam
 The goal of this course is to **understand**, **recognise**, and **apply**, all **Secure Coding Guidelines** (by Oracle for Java SE). Hence, the questions at the exam will be of the following form (or a combination thereof):
 
 - **Understand:** Explain with your own words which vulnerability a certain guideline tries to protect agains, and why it is important.
 - **Recognise:** Given a piece of code determine which vulnerability is exploitable and how it can be avoided.
 - **Apply:** Rewrite a piece of vulnerable code such that the vulnerability can not longer be exploited.

 ### Exotic Guidelines
 Some guidelines might involve topics of the Java language that you did not cover in previous courses. If this is the case, you can annotate the guideline with the following badge:
 ![UKN](https://img.shields.io/badge/UKN-Mattias.De.Wael-red.svg)
 ```![UKN](https://img.shields.io/badge/UKN-Mattias.De.Wael-red.svg)```

This is, one badge per student who is not familiar with the underlying content. Guidelines with a significant amount of these badges, **might** be excluded from the exam. However, use this badge wisely. This course still has to be a course of 3SP. If the badge is used to frequently, we will provide you with the necessary material to study the underlying concepts, and add these to the *exam material*, which means you can get question about the guidelines as well as the *new material*.