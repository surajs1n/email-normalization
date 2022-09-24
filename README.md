# Email Normalization

----

This is a light-weighted java library `(V1.0 version)` meant to validate and normalize a given morphed EmailId. Let us consider some below examples to understand it.

```java
EmailNormalizer.normalize("Suraj.Sn@gmail.com");      will return => surajsn@gmail.com
EmailNormalizer.normalize("Suraj.Sn+123@gmail.com");  will return => surajsn@gmail.com
EmailNormalizer.normalize("surajsn@gmail.com") ;      will return => surajsn@gmail.com
EmailNormalizer.normalize("SURAJSN@gmail.com") ;      will return => surajsn@gmail.com
```

### Motivation

Consider two security vulnerability scenarios:
1. A company onboards/creates a new user account based on the unique EmailId. If one user keeps on using morphed Emailds (which result into same final EmailId), then using this vulnerability he would be able to create multiple accounts and has capability to exploit the companies services. Even OTP verification system won't be able to help fix it.
2. The OTP system has a max-limit on number of retries on user requesting OTP again. Using a morphed EmailIds, a user can request humongous amount of OTP  resulting into over-utilization of company resources. 

Due to these two vulnerabilities there was a need to have a central concept that can fix these issues. 

I searched on Google to find some package(s)/library(s). Unfortunately, those were available for javascript/typescript and python projects. Therefore, I thought why shouldn't we build one for Java language as it is one of the most widely used languages.

This is the first-ever version (V1.0) of the library. There are still lot many areas we can improve on. If you want to contribute, please check out the **(How to contribute?)** section. Now, let us see the use-cases which are covered in this version of library and what all are out of scope.
#### In Scope
1. The library is capable of normalizing the EmailIds belonging to `[GMail, Outlook, AOL, Yahoo, ICloud, Zoho, Yandex, Tutanota]`
2. The library normalizes the Emails of GMail following below SOP:
   1. covert all the characters to lower-case.
   2. remove all the '.' from the email-prefix (string before `@`)
   3. truncate all the email-prefix characters appearing after `+` (if that exist in the email).
3. The library normalizes all the other EmailIds from other Email-Providers following the below SOP:
   1. covert all the characters to lower-case.

#### Out of Scope
1. The library doesn't validate the correctness of Email-Provider string. E.g:- for surajsn@googlemail.com, googlemail is the email-provider string.
2. The library doesn't validate the correctness of top-level domain string. E.g:- for surajsn@gmail.com, com is the top-level domain string.
3. The library doesn't allow external input to config check on email-normalization.
4. The library doesn't maintain severity level. User can't control the receiving exception from library or the warning messages. 

### Class-Level Diagram


### How to contribute?
