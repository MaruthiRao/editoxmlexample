# EDI using Smooks 1.5 framework with Camel Servlet REST and OSGi Blueprint example
=============================================

EDI 837 Health Care Claim: Example Web Service (using rest dsl) that accepts an edi health care claim as plain texts. Processes it to xml and puts on a queue for partner to pick up.

This example first process the edi text if recieves using Smooks framework to convert to xml.
Then throws on a ActiveMQ queue
Then a route waiting on the queue pops xml off queue and writes to a file.

For this example:
- Get the project 
    1. Change to a directory where the project is to be stored.
    2. Clone the project from github
         git clone https://github.com/thiswebs4u/editoxmlexample.git
    3. Open JBoss Developer Studio 9 and create a new workspace.
    4. Import project as a maven project.
    5. On the root pom.xml right click and Run As maven build...
    6. Use goals clean and install, this will install the artifact in the local repository.
    
Setup JBoss Fuse, jboss-fuse-full-6.3.0.redhat-015.zip used for this example
1. Download:
    https://repository.jboss.org/nexus/content/groups/ea/org/jboss/fuse/jboss-fuse-full/6.3.0.redhat-015/jboss-fuse-full-6.3.0.redhat-015.zip

2. Extract to desired location.
3. Start the console
    <Fuse Install>/bin/fuse
4. Install fabric.
    fabric:create --clean --force --wait-for-provisioning
     
    You might have to add a new user:
    No user found in etc/users.properties or specified as an option. Please specify one ...
    New user name: admin
    Password for admin:
    Verify password for admin:
5. Install profile for this example, edi-editoxml
     For windows:
         fabric:profile-import file:///C:/Development/Projects/restdsl/editoxmlexample/editoxmlprofile.zip

    Mac OSX or Linux
       fabric:profile-import file:/Development/Projects/restdsl/edi-to-xml-example/editoxmlprofile.zip

6. Add profile to root container.
     fabric:container-add-profile root edi-editoxml
     
7. Wait for fuse to provision

8. Go to your project directory, use curl to test example.
     curl http://localhost:8181/editoxml/rest/processClaim --upload-file input-message.edi

If all goes well you should see xml: You can also see the file camel writes out at location  <Fuse Install>/src/edixmlfiles/somefilename

