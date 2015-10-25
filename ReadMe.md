#### CONTACTS APPLICATION

An Java Application which can do some operate (add,list,search) on Contacts by using CLI.

###### Features

* Import Contacts from Files which has some rule and from any path on your system by using CLI . 
* Save your contacts in mongo db and merge them by name and lastName . 
* Add your mongo database name if you don't want application use default database which is name "Contacts" and defined in the App.
* List or search  by "name" and lastName the contacts on Mongo Database .

###### What you need 

* Mongo Server for store Data
* if you want to connect remote Mongo server then you can add -host , -port ,-db parameters to the main arguments of application.

##### Quick Start 

* Start Application 

Default DB properties :

    $ java -jar ContactApp.jar
    
    
Custom DB Properties :
    
    $ java -jar ContactApp.jar -host localhost -port 12712 -db MyDb
    
    
    
Add : 
    
for Example absolute path:
        
    $ -add /Users/username/DEV/WORKS/contact-app/src/test/resources/examples/example1.xml "/Users/examples folder/example1.xml"
     
for Example relative path : $ -add example1.xml ../example1.xml 
 
                                    
Search :

for example search  contacts by name OR lastName 

    $ -search name
    
    
for example search contacts by name AND lastName 
    
    $ -search name lastName
     
     
     
List : 
     
for example list all contacts
     
    $ -list 
     
##### Help 

* -add file1[ file2[ "file3"....]]  -> Import contact from files by some Rules. You can see following example how use it.

    -add example1.xml /Users/X/Y/Z/example2.xml

example1.xml

    <Contacts>
        <contact>
            <id>1</id>
            <name>Name1</name>
            <lastName>Lastname1</lastName>
            <phones>+90 555 222 33 44,+90 555 666 77 88</phones>
        </contact>
        <contact>
            <id>2</id>
            <name>Name2</name>
            <lastName>LastName2</lastName>
            <phones>+90 555 222 33 44,+90 555 666 77 88</phones>
        </contact>
    </Contacts>
    
    
* -search key1 [ key2] : you can search by name or lastName or both of them.
* -list  : You can see all contacts.
* quit : use it for exit from Application CLI
* --help : use help for learning how use commands. 
    






