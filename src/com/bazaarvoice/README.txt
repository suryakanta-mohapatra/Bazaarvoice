Problem:

Implement the DocumentSearch interface and prove that implementation works by creating tests using the TestNG framework.

Read in the schema.txt file to construct Schema object, where each row in schema.txt is an instance of SchemaRow class.

SchemaRow consists of three things - field-name, field-type, and search options. All those are fields in SchemaRow class.

Search Options:

fulltext - search by any word in the string, eg "what a beautiful day" is the input, search by word "what" or "day" should return this document.
lowercase - search by lowercase version of the string . eg if there is a value "RECORD", searching for "record" should match this document.


