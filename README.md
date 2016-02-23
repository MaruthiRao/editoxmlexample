# EDI using Smooks 1.5 framework with Camel Servlet REST and OSGi Blueprint example
=============================================


curl http://localhost:8181/editoxml/rest/processClaim --upload-file input-message.edi


EDI 837 Health Care Claim: Example Web Service that accepts an edi health care claim. Processes it to xml and puts on a queue for partner to pick up.

Download:
https://repository.jboss.org/nexus/content/groups/ea/org/jboss/fuse/jboss-fuse-full/6.3.0.redhat-015/jboss-fuse-full-6.3.0.redhat-015.zip

fabric:create --clean --force --wait-for-provisioning

fabric:profile-import file:/Development/Projects/restdsl/edi-to-xml-example/editoxmlprofile.zip

fabric:container-add-profile root edi-editoxml
















