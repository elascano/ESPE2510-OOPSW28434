const ContactModel = require('./database');

function isAlphabetic(str) {
  return /^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$/.test(str);
}

function computeAge(dateStr) {
  if (!/^\d{4}-\d{2}-\d{2}$/.test(dateStr)) return null;
  const d = new Date(dateStr);
  if (isNaN(d.getTime())) return null;
  const today = new Date();
  let age = today.getFullYear() - d.getFullYear();
  const m = today.getMonth() - d.getMonth();
  if (m < 0 || (m === 0 && today.getDate() < d.getDate())) {
    age--;
  }
  if (age < 0 || age > 120) return null;
  return age;
}

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
            };
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

const lblId = new JLabel("id:"); lblId.setBounds(20, 60, 80, 20); frame.add(lblId);
const txtId = new JLabel("(Auto)"); txtId.elem.style.justifyContent = "flex-start"; txtId.setBounds(110, 60, 100, 20); frame.add(txtId);

const lblName = new JLabel("First Name:"); lblName.setBounds(20, 90, 80, 20); frame.add(lblName);
const txtName = new JTextField(); txtName.setBounds(110, 90, 150, 20); frame.add(txtName);

const lblLast = new JLabel("Last Name:"); lblLast.setBounds(20, 120, 80, 20); frame.add(lblLast);
const txtLast = new JTextField(); txtLast.setBounds(110, 120, 150, 20); frame.add(txtLast);

const lblDate = new JLabel("Birth Date:"); lblDate.setBounds(20, 150, 80, 20); frame.add(lblDate);
const txtDate = new JTextField(); txtDate.elem.placeholder = "YYYY-MM-DD"; txtDate.setBounds(110, 150, 150, 20); frame.add(txtDate);

const lblAge = new JLabel("Age:"); lblAge.setBounds(20, 180, 80, 20); frame.add(lblAge);
const lblAgeVal = new JLabel("0"); lblAgeVal.elem.style.justifyContent = "flex-start"; lblAgeVal.setBounds(110, 180, 50, 20); frame.add(lblAgeVal);

const lblComm = new JLabel("Comments:"); lblComm.elem.style.justifyContent = "flex-start"; lblComm.setBounds(300, 60, 100, 20); frame.add(lblComm);
const txtComm = new JTextArea(); txtComm.setBounds(300, 85, 200, 100); frame.add(txtComm);

const lblType = new JLabel("Type:"); lblType.setBounds(50, 220, 50, 20); frame.add(lblType);
const cmbType = new JComboBox(["Family", "Friend", "Job", "Unknown"]); cmbType.setBounds(110, 220, 100, 22); frame.add(cmbType);

const lblSex = new JLabel("Sex:"); lblSex.setBounds(50, 260, 50, 20); frame.add(lblSex);
const rMale = new JRadioButton("Male", "sexGrp"); rMale.setBounds(110, 260, 80, 20); frame.add(rMale);
const rFemale = new JRadioButton("Female", "sexGrp", true); rFemale.setBounds(110, 285, 80, 20); frame.add(rFemale);

const lblHob = new JLabel("Hobbies:"); lblHob.setBounds(40, 320, 60, 20); frame.add(lblHob);
const lstHobbies = new JList(["Play Soccer", "Dijing", "Read", "Cook", "Swim", "Sing", "Play Instrument"]); lstHobbies.setBounds(110, 320, 150, 120); frame.add(lstHobbies);

const btnSave = new JButton("SAVE"); btnSave.setBounds(250, 500, 80, 25);
frame.add(btnSave);

txtDate.elem.addEventListener('input', () => {
    const val = txtDate.getText().trim();
    const age = computeAge(val);
    if (age !== null) {
        lblAgeVal.setText(age.toString());
    } else {
        lblAgeVal.setText("0");
    }
});

function validateFields() {
    const firstName = txtName.getText().trim();
    if (firstName === "" || !isAlphabetic(firstName)) {
        alert("Validation Error: First Name is required and must contain only letters.");
        setTimeout(() => {
            txtName.elem.blur();
            txtName.elem.focus({ preventScroll: true });
        }, 20);
        return false;
    }

    const lastName = txtLast.getText().trim();
    if (lastName === "" || !isAlphabetic(lastName)) {
        alert("Validation Error: Last Name is required and must contain only letters.");
        setTimeout(() => {
            txtLast.elem.blur();
            txtLast.elem.focus({ preventScroll: true });
        }, 20);
        return false;
    }

    const dateStr = txtDate.getText().trim();
    const age = computeAge(dateStr);
    if (age === null) {
        alert("Validation Error: Birth Date must be valid (YYYY-MM-DD) and age between 0 and 120.");
        setTimeout(() => {
            txtDate.elem.blur();
            txtDate.elem.focus({ preventScroll: true });
        }, 20);
        return false;
    }
    lblAgeVal.setText(age.toString());

    const typeValue = cmbType.getSelectedItem();
    if (!typeValue) {
        alert("Validation Error: Type is required.");
        setTimeout(() => {
            cmbType.elem.blur();
            cmbType.elem.focus({ preventScroll: true });
        }, 20);
        return false;
    }

    if (!rMale.isSelected() && !rFemale.isSelected()) {
        alert("Validation Error: Sex is required.");
        setTimeout(() => {
            rFemale.elem.blur();
            rFemale.elem.focus({ preventScroll: true });
        }, 20);
        return false;
    }

    const selectedHobbies = lstHobbies.getSelectedValues();
    if (!selectedHobbies || selectedHobbies.length === 0) {
        alert("Validation Error: Select at least one hobby.");
        return false;
    }

    return true;
}

function emptyFields() {
    txtName.setText("");
    txtLast.setText("");
    txtDate.setText("");
    lblAgeVal.setText("0");
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
            firstName: txtName.getText(),
            lastName: txtLast.getText(),
            birthDate: txtDate.getText(),
            age: parseInt(lblAgeVal.elem.innerText),
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
