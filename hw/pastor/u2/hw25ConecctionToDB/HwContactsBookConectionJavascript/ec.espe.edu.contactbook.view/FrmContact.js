const { 
    QMainWindow, QWidget, QLabel, QLineEdit, QPushButton, 
    QComboBox, QRadioButton, QDateEdit, QTextEdit, QListWidget,
    QMessageBox, FlexLayout, QScrollArea, ButtonRole 
} = require("@nodegui/nodegui");

const MongoConnection = require('../ec.espe.edu.contactbook.model/MongoConnection');
const Contact = require('../ec.espe.edu.contactbook.model/Contact.js');

class FrmContacts {
    constructor() {
        this.win = new QMainWindow();
        this.win.setWindowTitle("Contacts Book");
        this.win.resize(900, 700);

        this.centralWidget = new QWidget();
        this.centralWidget.setObjectName("root");
        const mainLayout = new FlexLayout();
        this.centralWidget.setLayout(mainLayout);
        
        this.centralWidget.setStyleSheet(`
            #root { background-color: #f0f0f0; width: 500%; height: 500%; }
        `);

        const scrollArea = new QScrollArea();
        scrollArea.setWidgetResizable(true);
        scrollArea.setInlineStyle("flex: 2; width: 500%;"); 

        this.scrollContent = new QWidget();
        this.scrollContent.setObjectName("mainPanel");
        this.rootLayout = new FlexLayout();
        this.scrollContent.setLayout(this.rootLayout);

        this.scrollContent.setStyleSheet(`
            #mainPanel { 
                background-color: #dcdcdc; 
                padding: 10px;
            }
            QLabel { color: #000080; font-size: 7px; font-weight: bold; margin-bottom: 1px; }
            QLineEdit { background-color: #ffffff; padding: 2.5px; margin-bottom: 5px; border: 1px solid #999; }
            QComboBox { margin-bottom: 5px; padding: 2.5px; }
            QTextEdit { background-color: #fff; margin-bottom: 5px; border: 1px solid #999; }
            QListWidget { background-color: #fff; margin-bottom: 5px; border: 1px solid #999; }
            QPushButton { 
                background-color: #e0e0e0; 
                color: #000; 
                padding: 4px; 
                border: 1px solid #666;
                font-weight: bold;
            }
            QPushButton:hover { background-color: #ccc; }
        `);

        scrollArea.setWidget(this.scrollContent);
        mainLayout.addWidget(scrollArea);

        this.initUI();
        this.setupEvents();
        
        this.win.setCentralWidget(this.centralWidget);
        this.win.show();
        global.win = this.win;
    }

    initUI() {
        this.lblTitle = new QLabel();
        this.lblTitle.setText("CONTACTS");
        this.lblTitle.setInlineStyle("font-size: 28px; color: #003366; align-self: flex-start; margin-bottom: 20px; font-weight: 800;");
        this.rootLayout.addWidget(this.lblTitle);

        const formContainer = new QWidget();
        const formLayout = new FlexLayout();
        formContainer.setLayout(formLayout);
        formContainer.setInlineStyle("width: 100%;"); 

        this.lblId = new QLabel(); this.lblId.setText("id:");
        this.txtId = new QLineEdit(); 
        formLayout.addWidget(this.lblId);
        formLayout.addWidget(this.txtId);

        this.lblDate = new QLabel(); this.lblDate.setText("Birth Day:");
        this.calendar = new QDateEdit(); 
        this.lblAge = new QLabel(); this.lblAge.setText("Age:");
        this.txtAge = new QLabel(); this.txtAge.setText("0"); 
        
        formLayout.addWidget(this.lblDate);
        formLayout.addWidget(this.calendar);
        formLayout.addWidget(this.lblAge);
        formLayout.addWidget(this.txtAge);

        this.lblFirst = new QLabel(); this.lblFirst.setText("First Name:");
        this.txtFirst = new QLineEdit();
        this.lblLast = new QLabel(); this.lblLast.setText("Last Name:");
        this.txtLast = new QLineEdit();
        
        formLayout.addWidget(this.lblFirst);
        formLayout.addWidget(this.txtFirst);
        formLayout.addWidget(this.lblLast);
        formLayout.addWidget(this.txtLast);

        this.lblType = new QLabel(); this.lblType.setText("Type:");
        this.cmbType = new QComboBox();
        this.cmbType.addItems(["Family", "Friend", "Job", "Unknown"]);
        formLayout.addWidget(this.lblType);
        formLayout.addWidget(this.cmbType);

        this.lblSex = new QLabel(); this.lblSex.setText("Sex:");
        this.radMale = new QRadioButton(); this.radMale.setText("Male");
        this.radFemale = new QRadioButton(); this.radFemale.setText("Female");

        const sexContainer = new QWidget();
        const sexLayout = new FlexLayout();
        sexContainer.setLayout(sexLayout);
        sexContainer.setInlineStyle(`flex-direction: row; margin-bottom: 10px;`);
        
        sexLayout.addWidget(this.radMale);
        sexLayout.addWidget(this.radFemale);

        formLayout.addWidget(this.lblSex);
        formLayout.addWidget(sexContainer);

        this.lblHobbies = new QLabel(); this.lblHobbies.setText("Hobbies:");
        this.lstHobbies = new QListWidget(); 
        this.lstHobbies.addItems(["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]);
        this.lstHobbies.setFixedHeight(100); 
        
        formLayout.addWidget(this.lblHobbies);
        formLayout.addWidget(this.lstHobbies);

        this.lblComments = new QLabel(); this.lblComments.setText("Comments:");
        this.txtComments = new QTextEdit(); 
        this.txtComments.setFixedHeight(80); 
        
        formLayout.addWidget(this.lblComments);
        formLayout.addWidget(this.txtComments);

        this.rootLayout.addWidget(formContainer);

        this.btnSave = new QPushButton();
        this.btnSave.setText("SAVE"); 
        this.btnSave.setCursor(13); 
        this.btnSave.setInlineStyle("width: 100px; height: 60px; background-color: #ccc; font-weight: bold; border: 1px solid #666;");
        this.rootLayout.addWidget(this.btnSave);
    }

    setupEvents() {
        this.calendar.addEventListener('dateChanged', () => {
            const date = this.calendar.date(); 
            const year = date.year();
            const currentYear = new Date().getFullYear();
            const age = currentYear - year;
            this.txtAge.setText(age.toString());
        });

        this.radMale.addEventListener('toggled', (checked) => {
            if(checked) this.radFemale.setChecked(false);
        });
        this.radFemale.addEventListener('toggled', (checked) => {
            if(checked) this.radMale.setChecked(false);
        });

        this.btnSave.addEventListener('clicked', async () => {
            this.btnSave.setEnabled(false);
            this.btnSave.setText("Saving...");
            try {
                await this.saveContact();
            } catch (error) {
                const errorMsg = new QMessageBox();
                errorMsg.setText("CRITICAL ERROR:\n" + error.message);
                errorMsg.exec();
            } finally {
                this.btnSave.setEnabled(true);
                this.btnSave.setText("SAVE");
            }
        });
    }

    readValues() {
        const selectedHobbyItem = this.lstHobbies.currentItem();
        const hobby = selectedHobbyItem ? selectedHobbyItem.text() : "";
        const sex = this.radMale.isChecked() ? "Male" : (this.radFemale.isChecked() ? "Female" : "");
        const rawContact = new Contact(
            this.txtId.text(),
            this.txtFirst.text(),
            this.txtLast.text(),
            this.txtAge.text(),
            this.cmbType.currentText(),
            sex,
            [hobby], 
            this.txtComments.toPlainText()
        );

        return rawContact;
    }

    async saveContact() {
        const contact = this.readValues();

        const dialog = new QMessageBox();
        dialog.setWindowTitle("Confirmation");
        dialog.setText("Confirm saving contact?");
        dialog.setInformativeText(JSON.stringify(contact, null, 2));
        const BtnYes = QMessageBox.Yes || 16384; 
        const BtnNo = QMessageBox.No || 65536;
        dialog.setStandardButtons(BtnYes | BtnNo);
        dialog.setDefaultButton(BtnYes);
        const result = dialog.exec();
        if (result === BtnYes) {
            try {
                const db = await MongoConnection.getDatabase();
                const collection = db.collection("Contact");
                const contactObject = Object.assign({}, contact); 

                await collection.insertOne(contactObject);
                
                const successMsg = new QMessageBox();
                successMsg.setText("YOUR CONTACT IS SAVED SUCCESSFULLY");
                successMsg.exec();
                
                this.emptyFields();
            } catch (err) {
                console.error(err);
                const errorMsg = new QMessageBox();
                errorMsg.setText("Error saving to DB: " + err.message);
                errorMsg.exec();
            }
        } else {
            console.log("Save cancelled by user");
        }
    }

    emptyFields() {
        this.txtId.setText("");
        this.txtFirst.setText("");
        this.txtLast.setText("");
        this.txtAge.setText("0");
        this.cmbType.setCurrentIndex(0);
        if(this.radMale.isChecked()) this.radMale.click(); 
        if(this.radFemale.isChecked()) this.radFemale.click();
        this.txtComments.setText("");
    } 
}

module.exports = FrmContacts;