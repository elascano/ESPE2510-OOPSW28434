const validModel = (text) => /^[A-Za-z ]+$/.test(text);
const validPrice = (text) => /^\d+(\.\d{1,2})?$/.test(text);

module.exports = { validModel, validPrice };