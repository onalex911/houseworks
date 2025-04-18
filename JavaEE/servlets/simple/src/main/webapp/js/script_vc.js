async function sendVCData() {
    const text = document.getElementById("main-text").value;

    const jsonData = JSON.stringify({text});
    let data = await SendJSONData(jsonData,"vow-cons-servlet");

    console.log(data);

    document.getElementById("result").innerHTML = data.error !== '' ? `<li>Vowels: <b>${data.vowels.num}</b> [${data.vowels.letters}]</li>` +
    `<li>Consonants: <b>${data.consonants.num}</b> [${data.consonants.letters}]</li>` +
    `<li>Punctuation marks: <b>${data.punctuations.num}</b> [${data.punctuations.letters}]</li>` : `ERROR: ${data.error}`;
}
