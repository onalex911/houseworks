// $('#myModal').on('shown.bs.modal', function () {
//       $('#myInput').trigger('focus')
//     })

let apiKey = '8b8ac412';
let url = 'https://www.omdbapi.com/';
let myForm = document.forms.myForm;
let list = document.querySelector('.list');
let alertSuccess = document.querySelector('.alert-success');
let alertDanger = document.querySelector('.alert-danger');


// let container = document.querySelector('.container');
myForm.addEventListener('submit',async function(e){
      e.preventDefault();
      alertDanger.style.setProperty('display','none');
      alertSuccess.style.setProperty('display','none');
      list.innerHTML = '';
      let title = myForm.title.value;
      //console.log('RADIO: '+myForm.flexRadio.value);
      let searchResult = await getMovies(title,myForm.flexRadio.value);
      //console.log(searchResult);
      if(searchResult.Response == 'True'){
            let movies = searchResult.Search;
            document.getElementById('movie-type-found').innerText = myForm.flexRadio.value;
            document.getElementById('movie-type-count').innerText = searchResult.totalResults;
            alertSuccess.style.setProperty('display','block');
            //console.log(movies);
            for(const movie of movies){
                  // console.log(movie.Title + ' ' + movie.imdbID);
                  let movieInfo = await getMoviesByID(movie.imdbID);
                  // console.log(movieInfo);
                  let moviePlot = 'N/A';
                  let movieYear = 'N/A';
                  if(movieInfo){
                        moviePlot = movieInfo.Plot;
                        movieYear = movieInfo.Year;
                  }           
                  list.innerHTML += `<div class="card col-2 m-2">
      <img class="card-img-top" src="${movie.Poster != 'N/A' ? movie.Poster : 'image_not_found.png'}" alt="${movie.Title}">
      <div class="card-body">
      <h5 class="card-title">${cutByWord(movie.Title,30)}</h5>
      <div class="movie-short-info">
            <p class="card-text">Year: ${movieYear}</p>
            <p class="card-text">${cutByWord(moviePlot,100)}</p>
      </div>
      <div class="box"><a id="${movie.imdbID}" href="#card" class="btn btn-primary">More info</a></div>
      </div>
      </div>`;      
            }
      }else{
            alertDanger.style.setProperty('display','block');
      }

      myForm.reset();
});

list.addEventListener('click',async function(e){
      //console.log(ee);
      if(e.target.classList.contains('btn')){
            let movieInfo = await getMoviesByID(e.target.id);            
            if(movieInfo){
                  document.getElementById('movie-info-image').setAttribute('src', movieInfo.Poster);
                  document.getElementById('movie-info-title').innerText = movieInfo.Title;
                  document.getElementById('movie-info-released').innerText = movieInfo.Released;
                  document.getElementById('movie-info-genre').innerText = movieInfo.Genre;
                  document.getElementById('movie-info-country').innerText = movieInfo.Country;
                  document.getElementById('movie-info-director').innerText = movieInfo.Director;
                  document.getElementById('movie-info-writer').innerText = movieInfo.Writer;
                  document.getElementById('movie-info-actors').innerText = movieInfo.Actors;
                  document.getElementById('movie-info-awards').innerText = movieInfo.Awards;
                  document.getElementById('movie-info-plot').innerText = movieInfo.Plot;
                  
            }   

      }
});

// let movies = getMovies('frog');
// console.log(movies.Search.length);

async function getMovies(searchStr,movieType = '') {
      let urlString = `${url}?s=${searchStr}&apikey=${apiKey}`;
      if(movieType != '' && movieType != 'undefined')
            urlString += `&type=${movieType}`;
      data = await fetch(urlString);
      //console.log(data);
      let result = await data.json();
      return result;     
}

async function getMoviesByID(id) {
      data = await fetch(`${url}?i=${id}&apikey=${apiKey}`);
      //console.log(data);
      let result = await data.json();
      return result;     
}

function cutByWord(stringToDo,maxLength){

      if(stringToDo.length <= maxLength)
      return stringToDo;
      
      let out = '';            
      let stringArr = stringToDo.split(' ');

      for (let i = 0; i < stringArr.length; i++) {                   
            if(out.length + stringArr[i].length + 1 > maxLength)
                  return out.substring(0, out.length - 1) + '...';
            else
                  out += (stringArr[i] + ' ');      
      }            
}