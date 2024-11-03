let apiKey = '8b8ac412';
let url = 'https://www.omdbapi.com/';
let myForm = document.forms.myForm;
let list = document.querySelector('.list');
let alert = document.querySelector('.alert');

// let container = document.querySelector('.container');
myForm.addEventListener('submit',async function(e){
      e.preventDefault();
      alert.style.setProperty('display','none');
      list.innerHTML = '';
      let title = myForm.title.value;
      //console.log(title);
      let searchResult = await getMovies(title);
      console.log(searchResult);
      if(searchResult.Response == 'True'){
            let movies = searchResult.Search;
            //console.log(movies);
            for(const movie of movies){
                  console.log(movie.Title);
                  list.innerHTML += `<div class="card col-3 m-2">
      <img class="card-img-top" src="${movie.Poster != 'N/A' ? movie.Poster : 'image_not_found.png'}" alt="Card image cap">
      <div class="card-body">
      <h5 class="card-title">${movie.Title}</h5>
      <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
      <a href="#" class="btn btn-primary">Go somewhere</a>
      </div>
      </div>`;
            }
      }else{
            alert.style.setProperty('display','block');
      }

      myForm.reset();
});
// let movies = getMovies('frog');
// console.log(movies.Search.length);

async function getMovies(searchStr) {
      data = await fetch(`${url}?s=${searchStr}&apikey=${apiKey}`);
      //console.log(data);
      let result = await data.json();
      return result;     
}