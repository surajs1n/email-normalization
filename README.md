# Email Normalization

----
This is a light-weighted java library `(V1.1 version)` meant to validate and normalize a given morphed EmailId. Let us consider below examples to understand it.

```java
EmailNormalizer emailNormalizer = EmailNormalizer.getInstance();

emailNormalizer.normalize("Suraj.Sn@gmail.com");      will return => surajsn@gmail.com
emailNormalizer.normalize("Suraj.Sn+123@gmail.com");  will return => surajsn@gmail.com
emailNormalizer.normalize("surajsn@gmail.com") ;      will return => surajsn@gmail.com
emailNormalizer.normalize("SURAJSN@gmail.com") ;      will return => surajsn@gmail.com
emailNormalizer.normalize("SURAJSN@OUTLOOK.com") ;    will return => surajsn@outlook.com
```

### Table of Content
1. How to use it?
2. Motivation
   1. In Scope
   2. Out of Scope
3. Class-Level Diagram
4. Write your own custom config file
5. How to contribute?

### How to use it?

1. Add the following dependency in your pom file
    ```xml
    <dependency>
        <groupId>io.github.surajs1n</groupId>
        <artifactId>email-normalization</artifactId>
        <version>1.1.0</version>
    </dependency>
    ```
2. Use `EmailNormalizer emailNormalizer = EmailNormalizer.getInstance()`, this would create an EmailNormalizer object with preinstalled config. Whereas, `1.1.0` onwards library version provides a flexibility to modify the handlers ordered-list at email-provider level. (Please check out the "Write your own custom config file" section for more details.)  
3. Pass an input emailId that you want to normalize: `emailNormalizer.normalize("Suraj.Sn12.3.23@gmail.com")`. It would return a normalized emailId response.

### Motivation

Consider two security vulnerability scenarios:
1. A company onboards/creates a new user account based on the unique EmailId. If one user keeps on using morphed EmailIds (which result in the same final EmailId), then using this vulnerability he would be able to create multiple accounts and can exploit the company's services. Even the OTP verification system won't be able to help fix it.
2. The OTP system has a max limit on the number of retries on the user requesting OTP again. Using a morphed EmailIds user can request a humongous amount of OTPs, resulting in over-utilization of company resources.

To resolve these two vulnerabilities there is a need to have a central concept that can fix these. I searched on Google for some package(s)/library(s). Unfortunately, those were available for javascript/typescript and python projects. Therefore, I thought, why shouldn't we build one for Java language as it is one of the most widely used languages?

It is the second revision `(V1.1)` of the library. There are still many areas we can improve on. If you want to contribute to it. Please check out the **(How to contribute?)** section.

Now, let us see the use cases that are covered in this version and what all are out of scope.

#### In Scope
1. The library is capable of normalizing the EmailIds belonging to `[GMail, Outlook, AOL, Yahoo, ICloud, Zoho, Yandex, Tutanota]`
2. The library supports custom config. It can be used to update the normalization logic per email-provider.
3. The library normalizes the Emails of GMail using below SOP (It uses in-built YAML config file):
   1. Convert all the characters to lower-case.
   2. Remove all the `.` from the email-prefix(string before `@`). E.g:- for `surajs.n@googlemail.com`, `surajs.n` is the email-prefix string.
   3. Truncate all the email-prefix characters appearing after `+` (if that exist in the email).
4. The library normalizes all the other EmailIds from other Email-Providers using the below SOP (It uses in-built YAML config file):
   1. Convert all the characters to lower-case.

#### Out of Scope
1. The library doesn't validate the correctness of email-provider string. E.g:- for `surajsn@googlemail.com`, `googlemail` is the email-provider string.
2. The library doesn't validate the correctness of top-level domain string. E.g:- for `surajsn@gmail.com`, `com` is the top-level domain string.

### Class-Level Diagram

Here is the class-level diagram of V1 version.
1. The `EmailNormalizer.normalize` is the entry point of the library.
2. The library utilizes central EmailHandlerFactory that has access to all ordered-list of EmailHandlers.
3. The `YAMLConfigReader` class reads the config.

![Email Normalization Class-Level Diagram](/src/main/resources/EmailNormalization.png)

### Write your own custom config file
Let us understand the available config construct before writing the custom file.
1. Library supports three EmailNormalizer Handler Type:
   1. TO_LOWERCASE - This handler converts every eligible character into its lowercase.
      ```bash
      E.g:-
      Input Email :- Surajs1N@gmail.com
      Output Email:- surajs1n@gmail.com
      ```
   2. REMOVE_CHARACTERS - This handler is responsible to remove noisy list of characters from the input. You would need to initialize `patternOfCharacterToBeRemoved` parameter.
      ```bash
      E.g:-
      Given: patternOfCharacterToBeRemoved = [.]
      Input email :- suraj.S.1.n@gmail.com
      Output email :- surajS1n@gmail.com   
      ```
   3. TRIM_SUFFIX - This handler trims the suffix of emailPrefix based on the given set of character string. You would need to initialize `trimRegexExpression`
      ```bash
      E.g:-
      Given :- trimRegexExpression = "\\+"
      Input Email  :- surajS.1N+11@gmail.com
      Output Email :- surajS.1N@gmail.com
      ```
2. The `emailProviderTypeListMap` is a global Map. It stores the email-provider name (key) to list of email handler-types (value). Have a look at below sample for reference.
   ```yaml
   emailProviderTypeListMap:
    GMAIL:
    - handlerType: TO_LOWERCASE
    - handlerType: REMOVE_CHARACTERS
      patternOfCharacterToBeRemoved: "[.]"
    - handlerType: TRIM_SUFFIX
      trimRegexExpression: "\\+"
    OUTLOOK:
    - handlerType: TO_LOWERCASE
    AOL:
    - handlerType: TO_LOWERCASE
    YAHOO:
    - handlerType: TO_LOWERCASE
   ```
Now, follow the below setups to introduce the custom logics:
1. Create a new YAML file.
2. Add an `emailProviderTypeListMap` map attribute, if not present.
3. Add your new email-Provider name (let's say `ABC`), after that for its value add the list of email-handler types following the above example.
   ```yaml
   emailProviderTypeListMap:
    ABC:
    - handlerType: TO_LOWERCASE
    - handlerType: REMOVE_CHARACTERS
      patternOfCharacterToBeRemoved: "[.~!#]"
   ```
4. If you want to overwrite an existing email-providers that comes with the library, then all you have to do is repeat Step-3 with the name of the existing email-provider. Below is an example.
   ```yaml
   emailProviderTypeListMap:
    OUTLOOK:
    - handlerType: TO_LOWERCASE
    - handlerType: REMOVE_CHARACTERS
      patternOfCharacterToBeRemoved: "[.$%]"
    YAHOO:
    - handlerType: TO_LOWERCASE
    - handlerType: TRIM_SUFFIX
      trimRegexExpression: "&"
   ```
   Here, Outlook and Yahoo's configs are overwritten. But, the config of all the other email-providers will remain unaffected.
5. Create EmailNormalizer object passing the reference of newly created YAML file. E.g:- `EmailNormalizer emailNormalizer = EmailNormalizer.getInstance(<file-path>)`.
6. Pass an input emailId that you want to normalize: `emailNormalizer.normalize("Suraj.Sn12.3.23@Outlook.com")`. It would return a normalized emailId response based on the result of library's and the custom config.

### How to contribute?
Since we wrote the initial version of the project. There a many areas where we are expecting contribution.
1. **Finding a bug/issue** - In case of an issue, incompleteness, vulnerability in the library. Please feel free to create an issue explaining the problem in details. The author(s) would take thing forward from there.
2. **Contribution through code changes** - In case if you want to contribute to this library through code changes, then we would prefer to first start a discussion through some channel (perhaps creating an issue) would work. Once we (you and the author(s) of the library) are on the same page. Please go ahead:
   1. Create forked repo out of it
   2. Push the changes on your remote branch
   3. Create a pull request against the `develop` branch. 
   4. The author will take care of approving and merging it into the `develop` branch.  
