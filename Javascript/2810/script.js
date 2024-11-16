let apiKey = '800ac23e';
let url = 'https://www.omdbapi.com/';
let myForm = document.forms.myForm;
let list = document.querySelector('.list');
let alertSuccess = document.querySelector('.alert-success');
let alertDanger = document.querySelector('.alert-danger');
let title = '';
let currentPage = 1;
let pagesLimit = 5;
let maxPages = 10;
let ul = document.querySelector('.pagination');

myForm.addEventListener('submit',async function(e){
      e.preventDefault();
      alertDanger.style.setProperty('display','none');
      alertSuccess.style.setProperty('display','none');
      list.innerHTML = '';
      title = myForm.title.value;
      renderMovieByTitle();
      
      myForm.reset();
});

async function renderMovieByTitle(page = 1) {

      let searchResult = await getMovies(myForm.flexRadio.value,page);
      let nav = document.querySelector("nav");

      if(searchResult.Response == 'True'){
            let movies = searchResult.Search;
            document.getElementById('movie-type-found').innerText = myForm.flexRadio.value;
            document.getElementById('movie-type-count').innerText = searchResult.totalResults;
            alertSuccess.style.setProperty('display','block');
            
            list.innerHTML = '';
            for(const movie of movies){                  
                  let movieInfo = await getMoviesByID(movie.imdbID);                  
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

            let count = searchResult.totalResults;
            let sizePage = Math.ceil(count / 10);            
            currentPage = page;

            localStorage.setItem('numMoviesPages',sizePage);
            renderPagination(sizePage);
            
            document.getElementById("pages-amount-input").value = pagesLimit;
            nav.style.setProperty("display","flex");
      }else{
            nav.style.setProperty("display","none");
            alertDanger.style.setProperty('display','block');
      }

  }
  

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

async function getMovies(movieType = '',page) {
      let urlString = `${url}?s=${title}&page=${page}&apikey=${apiKey}`;
      if(movieType != '' && movieType != 'undefined')
            urlString += `&type=${movieType}`;
      data = await fetch(urlString);
      let result = await data.json();
      return result;     
}

async function getMoviesByID(id) {
      data = await fetch(`${url}?i=${id}&apikey=${apiKey}`);
      
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

function renderPagination(pages) {
  
      ul.innerHTML = '';
      let newSize = (parseInt(currentPage) + parseInt(pagesLimit));
      console.log("pages = "+pages);

      if (parseInt(currentPage) > 1) {
          ul.innerHTML += '<li class="page-item"><a class="page-link" href="#">Previous</a></li>';
      }
  
      // for (let i = currentPage - pagesLimit; i <= newSize; i++) {
      for (let i = currentPage; i < newSize; i++) {
          if (i > 0 && i <= pages) {
              ul.innerHTML += `<li class="page-item ${i==currentPage?'active':''}"><a class="page-link" href="#">${i}</a></li>`;
          }
      }
  
      if (parseInt(pages) > parseInt(currentPage)) {
          ul.innerHTML += '<li class="page-item"><a class="page-link" href="#">Next</a></li>'
      }      
  
  }
  
  document.querySelector('.pagination').addEventListener('click', function(e) {

            switch (e.target.innerText) {
            case 'Previous':
                  page = parseInt(currentPage) - 1;
                  break;
            case 'Next':
                  page = parseInt(currentPage) + 1;
                  break;
            default:
                  page = e.target.innerText;
                  break;
            }
            console.log(page);
            renderMovieByTitle(page);
  });

  document.getElementById("pages-amount-input").addEventListener('change', function(e) {      
      
      let currentNumPages = parseInt(localStorage.getItem("numMoviesPages"));      
      let oldPagesLimit = pagesLimit;
      let newPagesValue = parseInt(e.target.value);

      if(newPagesValue <= 1){
            pagesLimit = 1;
      }
      else if(newPagesValue <= currentNumPages){
            // pagesLimit = newPagesValue%2 == 0 ? newPagesValue/2 : (newPagesValue - 1)/2;
            pagesLimit = newPagesValue;
      }else{
            e.target.value = currentNumPages;
      }
      
      // console.log("new pagesLimit = " + pagesLimit);
      if(oldPagesLimit != pagesLimit)
            renderPagination(currentNumPages);

});