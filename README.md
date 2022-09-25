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
1. A company onboards/creates a new user account based on the unique EmailId. If one user keeps on using morphed EmailIds (which result in the same final EmailId), then using this vulnerability he would be able to create multiple accounts and can exploit the company's services. Even the OTP verification system won't be able to help fix it.
2. The OTP system has a max limit on the number of retries on the user requesting OTP again. Using a morphed EmailIds user can request a humongous amount of OTP, resulting in over-utilization of company resources.

To resolve these two vulnerabilities there is a need to have a central concept that can fix these. I searched on Google for some package(s)/library(s). Unfortunately, those were available for javascript/typescript and python projects. Therefore, I thought, why shouldn't we build one for Java language as it is one of the most widely used languages?

It is the first-ever version `(V1.0)` of the library. There are still many areas we can improve on. If you want to contribute to it. Please check out the **(How to contribute?)** section.

Now, let us see the use cases that are covered in this version and what all are out of scope.

#### In Scope
1. The library is capable of normalizing the EmailIds belonging to `[GMail, Outlook, AOL, Yahoo, ICloud, Zoho, Yandex, Tutanota]`
2. The library normalizes the Emails of GMail using below SOP:
   1. Covert all the characters to lower-case.
   2. Remove all the `.` from the email-prefix(string before `@`). E.g:- for `surajs.n@googlemail.com`, `surajs.n` is the email-prefix string.
   3. Truncate all the email-prefix characters appearing after `+` (if that exist in the email).
3. The library normalizes all the other EmailIds from other Email-Providers using the below SOP:
   1. covert all the characters to lower-case.

#### Out of Scope
1. The library doesn't validate the correctness of email-provider string. E.g:- for `surajsn@googlemail.com`, `googlemail` is the email-provider string.
2. The library doesn't validate the correctness of top-level domain string. E.g:- for `surajsn@gmail.com`, `com` is the top-level domain string.
3. The library doesn't support custom config to modify email-normalization logic.
### Class-Level Diagram

Here is the class-level diagram of V1 version.
1. The `EmailNormalizer.normalize` is the entry point of the library.
2. The library utilizes central EmailHandlerFactory that has access to all ordered-list of EmailHandlers.
3. The `EmailValidator` class carries all the checks to validate a given input.

![Email Normalization Class-Level Diagram](\src\main\resources\EmailNormalization.png)

### How to contribute?
Since we wrote the initial version of the project. There a many areas where we are expecting contribution.
1. **Finding a bug/issue** - In case of an issue, incompleteness, vulnerability in the library. Please feel free to create an issue explaining the problem in details. The author(s) would take thing forward from there.
2. **Contribution through code changes** - In case if you want to contribute to this library through code changes, then we would prefer to first start a discussion through some channel (perhaps creating an issue) would work. Once we (you and the author(s) of the library) are on the same page. Please go ahead:
   1. Create forked repo out of it
   2. Push the changes on your remote branch
   3. Create a pull request against the `master` branch. 
   4. The author will take care of approving and merging it into the `master` branch.  
