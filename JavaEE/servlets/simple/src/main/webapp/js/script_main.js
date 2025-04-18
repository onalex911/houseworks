function isEmpty(str){
    return isNaN(str) || str === 'undefined' || str.trim() === '';
}

async function SendJSONData(inputData,servletName){

    data = await fetch(servletName, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: inputData
    })
        .then(response => response.json())
        .then(data => {
            return data;
        })
        .catch(error => console.error('Error:', error));
    return data;
}