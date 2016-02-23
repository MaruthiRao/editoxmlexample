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
<Segments>
        <InterchangeControlHeader>
                <AuthorizationInformationQualifier>00</AuthorizationInformationQualifier>
                <AuthorizationInformation>          </AuthorizationInformation>
                <SecurityInformationQualifier>00</SecurityInformationQualifier>
                <SecurityInformation>          </SecurityInformation>
                <InterchangeIDQualifier>ZZ</InterchangeIDQualifier>
                <InterchangeSenderID>SENSITIVEDATA  </InterchangeSenderID>
                <InterchangeIDQualifier>01</InterchangeIDQualifier>
                <InterchangeReceiverID>SENSITIVE      </InterchangeReceiverID>
                <InterchangeDate>120801</InterchangeDate>
                <InterchangeTime>0400</InterchangeTime>
                <InterchangeControlStandardsIdentifier>^</InterchangeControlStandardsIdentifier>
                <InterchangeControlVersionNumber>00501</InterchangeControlVersionNumber>
                <InterchangeControlNumber>100000094</InterchangeControlNumber>
                <AcknowledgmentRequested>1</AcknowledgmentRequested>
                <UsageIndicator>T</UsageIndicator>
                <ComponentElementSeparator>:~</ComponentElementSeparator>
        </InterchangeControlHeader>
        <FunctionalGroupHeader>
                <FunctionalIdentifierCode>HC</FunctionalIdentifierCode>
                <ApplicationSendersCode>SENSIT</ApplicationSendersCode>
                <ApplicationReceiversCode>SENSITIVE</ApplicationReceiversCode>
                <Date>20120801</Date>
                <Time>0400</Time>
                <GroupControlNumber>94</GroupControlNumber>
                <ResponsibleAgencyCode>X</ResponsibleAgencyCode>
                <VersionReleaseIndustryIdentifierCode>005010X222A1~</VersionReleaseIndustryIdentifierCode>
        </FunctionalGroupHeader>
        <TransactionSetHeader>
                <TransactionSetIdentifierCode>837</TransactionSetIdentifierCode>
                <TransactionSetControlNumber>100000094~</TransactionSetControlNumber>
        </TransactionSetHeader>
        <BeginningOfHierarchicalTransaction>
                <HierarchicalStructureCode>0019</HierarchicalStructureCode>
                <TransactionSetPurposeCode>00</TransactionSetPurposeCode>
                <OriginatorApplicationTransactionIdentifier>100000094</OriginatorApplicationTransactionIdentifier>
                <TransactionSetCreationDate>20120801</TransactionSetCreationDate>
                <TransactionSetCreationTime>0400</TransactionSetCreationTime>
                <ClaimOrEncounterIdentifier>CH~</ClaimOrEncounterIdentifier>
        </BeginningOfHierarchicalTransaction>
        <SubmitterName>
                <EntityIdentifierCode>41</EntityIdentifierCode>
                <EntityTypeQualifier>1</EntityTypeQualifier>
                <SubmitterLastOrOrganizationName>PROVIDER</SubmitterLastOrOrganizationName>
                <SubmitterFirstName>DEMO</SubmitterFirstName>
                <SubmitterMiddleName>46</SubmitterMiddleName>
                <IdentificationCodeQualifier>1856269260</IdentificationCodeQualifier>
                <SubmitterIdentifier>~</SubmitterIdentifier>
        </SubmitterName>
        <SubmitterEDIContactInformation>
                <ContactFunctionCode>IC</ContactFunctionCode>
                <SubmitterContactName>ClearEHR.com</SubmitterContactName>
                <CommunicationNumberQualifier>EM</CommunicationNumberQualifier>
                <CommunicationNumber>troy@clearehr.com</CommunicationNumber>
                <CommunicationNumber>~</CommunicationNumber>
        </SubmitterEDIContactInformation>
        <ReceiverName>
                <EntityIdentifierCode>40</EntityIdentifierCode>
                <EntityTypeQualifier>2</EntityTypeQualifier>
                <ReceiverName>BLUE CROSS BLUE SHIELD OF TEXAS (HCSC)</ReceiverName>
                <InformationReceiverIdentificationNumber>46</InformationReceiverIdentificationNumber>
                <ReceiverPrimaryIdentifier>84980~</ReceiverPrimaryIdentifier>
        </ReceiverName>
        <HL_BillingPayToProviderHierarchicalLevel>
                <HierarchicalIDNumber>1</HierarchicalIDNumber>
                <HierarchicalLevelCode>20</HierarchicalLevelCode>
                <HierarchicalChildCode>1~</HierarchicalChildCode>
        </HL_BillingPayToProviderHierarchicalLevel>
        <BillingProviderName>
                <EntityIdentifierCode>85</EntityIdentifierCode>
                <EntityTypeQualifier>1</EntityTypeQualifier>
                <BillingProviderLastOrOrganizationalName>Provider</BillingProviderLastOrOrganizationalName>
                <IdentificationCodeQualifier>XX</IdentificationCodeQualifier>
                <BillingProviderIdentifier>1856269260~</BillingProviderIdentifier>
        </BillingProviderName>
        <BillingProviderAddress>
                <BillingProviderAddressLine>Address 1</BillingProviderAddressLine>
                <BillingProviderAddressLine>Address 2~</BillingProviderAddressLine>
        </BillingProviderAddress>
        <BillingProviderCityStateZIPCode>
                <BillingProviderCityName>Houston</BillingProviderCityName>
                <BillingProviderStateOrProvinceCode>TX</BillingProviderStateOrProvinceCode>
                <BillingProviderPostalZoneOrZIPCode>77027</BillingProviderPostalZoneOrZIPCode>
                <CountryCode>US~</CountryCode>
        </BillingProviderCityStateZIPCode>
        <BillingProviderSecondaryIdentification>
                <ReferenceIdentificationQualifier>SY</ReferenceIdentificationQualifier>
                <BillingProviderAdditionalIdentifier>123456789~</BillingProviderAdditionalIdentifier>
        </BillingProviderSecondaryIdentification>
        <SubscriberHierarchicalLevel>
                <HierarchicalIDNumber>2</HierarchicalIDNumber>
                <HierarchicalParentIDNumber>1</HierarchicalParentIDNumber>
                <HierarchicalLevelCode>22</HierarchicalLevelCode>
                <HierarchicalChildCode>0~</HierarchicalChildCode>
        </SubscriberHierarchicalLevel>
        <SubscriberInformation>
                <PayerResponsibilitySequenceNumberCode>P</PayerResponsibilitySequenceNumberCode>
                <IndividualRelationshipCode>18</IndividualRelationshipCode>
                <InsuredGroupOrPolicyNumber>216000</InsuredGroupOrPolicyNumber>
                <ClaimFilingIndicatorCode>ZZ~</ClaimFilingIndicatorCode>
        </SubscriberInformation>
        <SubscriberName>
                <EntityIdentifierCode>IL</EntityIdentifierCode>
                <EntityTypeQualifier>1</EntityTypeQualifier>
                <SubscriberLastName>Client5</SubscriberLastName>
                <SubscriberFirstName>Demo</SubscriberFirstName>
                <SubscriberMiddleName>MI</SubscriberMiddleName>
                <SubscriberNameSuffix>12054054</SubscriberNameSuffix>
                <SubscriberPrimaryIdentifier>~</SubscriberPrimaryIdentifier>
        </SubscriberName>
        <SubscriberAddress>
                <SubscriberAddressLine>1234 lucky lane</SubscriberAddressLine>
                <SubscriberAddressLine>~</SubscriberAddressLine>
        </SubscriberAddress>
        <SubscriberCityStateZIPCode>
                <SubscriberCityName>Houston</SubscriberCityName>
                <WPC837Q34030D_2010BA_N402_SubscriberStateCode>TX</WPC837Q34030D_2010BA_N402_SubscriberStateCode>
                <SubscriberPostalZoneOrZIPCode>77007</SubscriberPostalZoneOrZIPCode>
                <CountryCode>~</CountryCode>
        </SubscriberCityStateZIPCode>
        <SubscriberDemographicInformation>
                <DateTimePeriodFormatQualifier>D8</DateTimePeriodFormatQualifier>
                <SubscriberBirthDate>19790302</SubscriberBirthDate>
                <SubscriberGenderCode>F~</SubscriberGenderCode>
        </SubscriberDemographicInformation>
        <CreditDebitCardAccountHolderName>
                <EntityIdentifierCode>PR</EntityIdentifierCode>
                <EntityTypeQualifier>2</EntityTypeQualifier>
                <CreditOrDebitCardHolderLastOrOrganizationalName>BLUE CROSS BLUE SHIELD OF TEXAS (HCSC)</CreditOrDebitCardHolderLastOrOrganizationalName>
                <CreditOrDebitCardHolderFirstName>PI</CreditOrDebitCardHolderFirstName>
                <CreditOrDebitCardHolderMiddleName>84980</CreditOrDebitCardHolderMiddleName>
                <CreditOrDebitCardNumber>~</CreditOrDebitCardNumber>
        </CreditDebitCardAccountHolderName>
        <CreditDebitCardAccountHolderName>
                <PatientAccountNumber>28</PatientAccountNumber>
                <TotalClaimChargeAmount>500</TotalClaimChargeAmount>
                <BenefitsAssignmentCertificationIndicator>12:B:1</BenefitsAssignmentCertificationIndicator>
                <ReleaseOfInformationCode>Y</ReleaseOfInformationCode>
                <ExplanationOfBenefitsIndicator>A</ExplanationOfBenefitsIndicator>
                <DelayReasonCode>Y</DelayReasonCode>
                <HealthCareServiceLocationInformation>Y~AMT~</HealthCareServiceLocationInformation>
        </CreditDebitCardAccountHolderName>
</Segments>















