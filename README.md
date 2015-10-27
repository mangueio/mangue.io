# mangue.io
Mangue Cloud APIs

Install and run MySQL on the default port
Install and run Redis on the default port
Install and run Mongo on port 9998

MongoDB
to use 'mangue' mongodb settings, make sure that you run mongod server using de '/mongo.conf' and that theand that the directories listed in the configuration file actually exist on your system's drive'.

If you want to use mongodb on the default settings (as a system service), change the application-dev.properties file, search mongodb settings and change the port to 27017.

MySQL
create mangue_quartz scheme
run the following command on mysql terminal to add permissions to the mangue user:
. GRANT ALL ON * TO mangue_quartz mangue @ localhost IDENTIFIED BY 'mangue';

Clone the https://github.com/mangueio/mangue.io project | git@github.com: mangueio / mangue.io.git

or update to get the latest version: 'git pull'

Import the project using IntelliJ Import Wizard and set the project as a 'maven project'

Intellij: ctrl + alt + s -> Keymap -> Keymaps, to set to eclipse -> ok # optional settings for eclipse keymappings

sidebar -> projects -> make sure that the pom.xml file (mangue) has the maven icon (an M). If not, right click pom.xml and select the last menu item to 'add as maven project'.

If the projects sidebar is not visible -> Alt + 1

Make sure that the project does not contain compilation errors.

If errors exist, make sure that maven was successfully imported:
Ctrl + Alt + S -> Search Box: "Maven" -> Build, exection, Deployment> Build Tools -> Maven> Importing> Import Maven Projects atomatically

If errors still exist, check the project modules:
Ctrl + Shift + Alt + S> Modules> remove all the modules that are not mangue (eg, main, test) ... just mangue should be listed.

Make sure artifacts and libraries are imported and visible in the project.

If the project still shows compilation errors, Top Menu> View> Tool Windows> Maven Projects> Mangrove> Lifecycle> Right to Clean> Run 'mangrove [clean]'; then Right Compile> Run 'mangrove [compile]'

Set the running class:
Top Menu> Run> Edit Configurations ...> + > Application > configure the class name: io.mangue.Application and name the configuration > Ok

If you can not run the project, create an issue on github:
https://github.com/mangueio/mangue.io/issues
