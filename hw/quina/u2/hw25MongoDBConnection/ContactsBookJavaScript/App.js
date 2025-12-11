const ContactModel = require('./database');

const root = document.body;
root.style.fontFamily = "Tahoma, sans-serif";
root.style.fontSize = "11px";
root.style.backgroundColor = "#ece9d8";

class Component {
    constructor(domType) { 
        this.elem = document.createElement(domType);
        this.elem.style.position = "absolute"; 
        this.elem.style.boxSizing = "border-box";
    }
    setBounds(x, y, w, h) {
        this.elem.style.left = x + "px";
        this.elem.style.top = y + "px";
        this.elem.style.width = w + "px";
        this.elem.style.height = h + "px";
    }
    setText(t) { this.elem.innerText = t; }
    requestFocus() { this.elem.focus(); }
}

class JLabel extends Component {
    constructor(text) {
        super("div");
        this.setText(text);
        this.elem.style.display = "flex";
        this.elem.style.alignItems = "center";
        this.elem.style.justifyContent = "flex-end";
    }
}

class JTextField extends Component {
    constructor() {
        super("input");
        this.elem.style.border = "1px solid #7F9DB9";
        this.elem.style.padding = "2px";
    }
    getText() { return this.elem.value; }
    setText(t) { this.elem.value = t; }
}

class JTextArea extends Component {
    constructor() {
        super("textarea");
        this.elem.style.border = "1px solid #7F9DB9";
        this.elem.style.resize = "none";
    }
    getText() { return this.elem.value; }
    setText(t) { this.elem.value = t; }
}

class JComboBox extends Component {
    constructor(items) {
        super("select");
        items.forEach(i => {
            let opt = document.createElement("option");
            opt.innerText = i;
            this.elem.appendChild(opt);
        });
    }
    getSelectedItem() { return this.elem.value; }
    setSelectedIndex(i) { this.elem.selectedIndex = i; }
}

class JRadioButton extends Component {
    constructor(text, group, checked=false) {
        super("div");
        this.elem.style.display = "flex";
        this.elem.style.alignItems = "center";
        
        let radio = document.createElement("input");
        radio.type = "radio";
        radio.name = group;
        radio.checked = checked;
        this.radioBtn = radio;
        
        let lbl = document.createElement("span");
        lbl.innerText = text;
        lbl.style.marginLeft = "5px";

        this.elem.appendChild(radio);
        this.elem.appendChild(lbl);
        this.elem.style.border = "none";
    }
    isSelected() { return this.radioBtn.checked; }
    setSelected(b) { this.radioBtn.checked = b; }
}

class JList extends Component {
    constructor(items) {
        super("div");
        this.elem.style.border = "1px solid #7F9DB9";
        this.elem.style.background = "white";
        this.elem.style.overflowY = "scroll";
        this.selectedItems = [];
        this.divs = [];

        items.forEach(item => {
            let div = document.createElement("div");
            div.innerText = item;
            div.style.padding = "2px";
            div.style.cursor = "default";
            div.onclick = () => {
                if(div.style.background === "rgb(10, 36, 106)") {
                    div.style.background = "white";
                    div.style.color = "black";
                    this.selectedItems = this.selectedItems.filter(i => i !== item);
                } else {
                    div.style.background = "#0A246A";
                    div.style.color = "white";
                    this.selectedItems.push(item);
                }
            }
            this.elem.appendChild(div);
            this.divs.push(div);
        });
    }
    getSelectedValues() { return this.selectedItems; }
    clearSelection() {
        this.selectedItems = [];
        this.divs.forEach(d => {
            d.style.background = "white";
            d.style.color = "black";
        });
    }
}

class JButton extends Component {
    constructor(text) {
        super("button");
        this.setText(text);
        this.elem.style.background = "#ece9d8";
        this.elem.style.border = "1px solid #003c74";
        this.elem.style.cursor = "pointer";
    }
    addActionListener(fn) { this.elem.onclick = fn; }
}

class JFrame {
    constructor(title) {
        document.title = title;
        this.panel = document.createElement("div");
        this.panel.style.width = "100%";
        this.panel.style.height = "100%";
        root.appendChild(this.panel);
    }
    add(comp) { this.panel.appendChild(comp.elem); }
}

const frame = new JFrame("Contacts Book");

const lblTitle = new JLabel("CONTACTS");
lblTitle.elem.style.fontSize = "18px";
lblTitle.elem.style.justifyContent = "center";
lblTitle.setBounds(0, 20, 580, 30);
frame.add(lblTitle);

const lblId = new JLabel("id:");
lblId.setBounds(20, 60, 80, 20);
frame.add(lblId);

const txtId = new JTextField();
txtId.setBounds(110, 60, 100, 20);
frame.add(txtId);

const lblName = new JLabel("First Name:");
lblName.setBounds(20, 90, 80, 20);
frame.add(lblName);

const txtName = new JTextField();
txtName.setBounds(110, 90, 150, 20);
frame.add(txtName);

const lblLast = new JLabel("Last Name:");
lblLast.setBounds(20, 120, 80, 20);
frame.add(lblLast);

const txtLast = new JTextField();
txtLast.setBounds(110, 120, 150, 20);
frame.add(txtLast);

const lblDate = new JLabel("Birth Date:");
lblDate.setBounds(20, 150, 80, 20);
frame.add(lblDate);

const txtDate = new JTextField();
txtDate.elem.placeholder = "YYYY-MM-DD";
txtDate.setBounds(110, 150, 150, 20);
frame.add(txtDate);

const lblAge = new JLabel("Age:");
lblAge.setBounds(20, 180, 80, 20);
frame.add(lblAge);

const txtAge = new JTextField();
txtAge.setBounds(110, 180, 50, 20);
frame.add(txtAge);

const lblComm = new JLabel("Comments:");
lblComm.elem.style.justifyContent = "flex-start";
lblComm.setBounds(300, 60, 100, 20);
frame.add(lblComm);

const txtComm = new JTextArea();
txtComm.setBounds(300, 85, 200, 100);
frame.add(txtComm);

const lblType = new JLabel("Type:");
lblType.setBounds(50, 220, 50, 20);
frame.add(lblType);

const cmbType = new JComboBox(["Family", "Friend", "Job", "Unknown"]);
cmbType.setBounds(110, 220, 100, 22);
frame.add(cmbType);

const lblSex = new JLabel("Sex:");
lblSex.setBounds(50, 260, 50, 20);
frame.add(lblSex);

const rMale = new JRadioButton("Male", "sexGrp");
rMale.setBounds(110, 260, 80, 20);
frame.add(rMale);

const rFemale = new JRadioButton("Female", "sexGrp", true);
rFemale.setBounds(110, 285, 80, 20);
frame.add(rFemale);

const lblHob = new JLabel("Hobbies:");
lblHob.setBounds(40, 320, 60, 20);
frame.add(lblHob);

const lstHobbies = new JList(["Play Soccer", "Dijing", "Read", "Cook", "Swim", "Sing", "Play Instrument"]);
lstHobbies.setBounds(110, 320, 150, 120);
frame.add(lstHobbies);

const btnSave = new JButton("SAVE");
btnSave.setBounds(250, 500, 80, 25);
frame.add(btnSave);

function validateFields() {
    const nameRegex = /^(?!\s*$)[a-zA-Z\s]+$/;
    const dateRegex = /^\d{4}-\d{2}-\d{2}$/;
    const numberRegex = /^\d+$/;

    const firstName = txtName.getText().trim();
    if(firstName === "") {
        alert("Validation Error: First Name is required.");
        txtName.requestFocus();
        return false;
    }
    
    if (numberRegex.test(firstName)) {
        alert("Validation Error: First Name cannot be only numbers.");
        txtName.requestFocus();
        return false;
    }

    const lastName = txtLast.getText().trim();
    if(lastName === "") {
        alert("Validation Error: Last Name is required.");
        txtLast.requestFocus();
        return false;
    }
    
    if (numberRegex.test(lastName)) {
        alert("Validation Error: Last Name cannot be only numbers.");
        txtLast.requestFocus();
        return false;
    }

    if(!dateRegex.test(txtDate.getText())) {
        alert("Validation Error: Birth Date must be in format YYYY-MM-DD.");
        txtDate.requestFocus();
        return false;
    }

    const ageValue = txtAge.getText().trim();
    const parsedAge = parseInt(ageValue);
    if(ageValue === "" || isNaN(parsedAge) || parsedAge <= 0) {
        alert("Validation Error: Age must be a positive number.");
        txtAge.requestFocus();
        return false;
    }

    return true;
}

function emptyFields() {
    txtId.setText("");
    txtName.setText("");
    txtLast.setText("");
    txtDate.setText("");
    txtAge.setText("");
    txtComm.setText("");
    cmbType.setSelectedIndex(0);
    rFemale.setSelected(true);
    lstHobbies.clearSelection();
}

btnSave.addActionListener(async () => {
    if(!validateFields()) return;

    let confirmSave = confirm("Are you sure you want to save this contact to MongoDB?");
    if(!confirmSave) return;

    try {
        const newContact = new ContactModel({
            id: txtId.getText(),
            firstName: txtName.getText(),
            lastName: txtLast.getText(),
            birthDate: txtDate.getText(),
            age: parseInt(txtAge.getText()),
            type: cmbType.getSelectedItem(),
            sex: rMale.isSelected() ? "Male" : "Female",
            hobbies: lstHobbies.getSelectedValues(),
            comments: txtComm.getText()
        });

        await newContact.save();
        
        alert("SUCCESS! Contact saved to MongoDB Atlas.");
        emptyFields();

    } catch (error) {
        console.error(error);
        alert("ERROR: Could not save to database.\n" + error.message);
    }
});
