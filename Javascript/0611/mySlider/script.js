let imgPath = 'images/';
let imagesArray = ['01.jpg','02.jpg','03.jpg','04.jpg','05.jpg','06.jpg','07.jpg','08.jpg','09.jpg',
'10.jpg','11.png','12.jpg','13.jpg','14.jpg','15.jpg','16.jpg','17.jpg','18.jpg','19.jpg',
'20.jpg','21.jpg','22.jpg','23.jpg','24.jpg','25.jpg'];
let imagesNum = imagesArray.length;
let playing = false;
let imageMaximized = false;
let currentNum = 0;


let $firstButton = $('#first');
let $previousButton = $('#previous');
let $pausePlayButton = $('#pause-play');
let $nextButton = $('#next');
let $lastButton = $('#last');
let $mainImage = $('#main-image');
let $maxImage = $('#modal-img');

let colorUiEnabled = 'rgba(0,0,0,0.5)';
let colorUiDisabled = 'rgba(0,0,0,0.2)';

initSelectors(currentNum);

console.log($(window).width() + ' x ' + $(window).height());
console.log(currentNum);
let maxWidth = 800;
let maxHeight = $(window).height() > 800 ? 600 : 450;
$('.img-container').css('height',maxHeight+10)
showImage(parseInt(currentNum));

$('.container').on('click',function(e){
      if(e.target.classList.contains('indicator')){
            let curId = parseInt(e.target.id.substring(4));
            console.log(curId);
            showImage(curId);

      }else if(e.target.id == 'first'){
            showImage(0);

      }else if(e.target.id == 'last'){
            showImage(imagesNum - 1);

      }else if(e.target.id == 'previous'){
            showImage(currentNum - 1);

      }else if(e.target.id == 'next'){
            showImage(currentNum + 1);

      }else if(e.target.id == 'pause-play'){
            if (playing) pauseSlideShow();
            else playSlideShow();

      }else if(e.target.id == 'maximize'){
            imageMaximized = true;
            showMaxImage(currentNum);
            $('.modal-area').css('display','flex');
      }
});

$('.modal-area').on('click',function(e){
      if(e.target.id == 'minimize'){
            imageMaximized = false;
            $('.modal-area').css('display','none');
            showImage(currentNum);
            initSelectors(currentNum);

      }else if(e.target.id == 'max-previous'){
            console.log('previous');
            showMaxImage(currentNum - 1);

      }else if(e.target.id == 'max-next'){
            console.log('next');
            showMaxImage(currentNum + 1);
      }
});

function nextSlide() {
      if(playing && imageMaximized){
            pauseSlideShow();
      }else
            showImage(currentNum + 1);
      
}

function pauseSlideShow() {
      $pausePlayButton.attr('class', 'fa-solid fa-play');
      $pausePlayButton.attr('title', 'запустить слайдшоу');
      playing = false;
      clearInterval(sliderInterval);
}
  
function playSlideShow() {
      $pausePlayButton.attr('class', 'fa-solid fa-pause');
      $pausePlayButton.attr('title', 'остановить слайдшоу');
      playing = true;
      sliderInterval = setInterval(nextSlide, 8000);
}
  
function showImage(n){
      
      if(!playing && (n < 0 || n > imagesNum-1))
            return;
      
            $mainImage.fadeOut(1000);
            
            // console.log('currentNum = '+currentNum);
            $(`#img_${currentNum}`).removeClass('fas active');
            $(`#img_${currentNum}`).addClass('far');
            currentNum = (imagesNum + n)%imagesNum;
            // currentNum = n;
            $(`#img_${currentNum}`).removeClass('far');
            $(`#img_${currentNum}`).addClass('fas active');
            console.log('currentNum = '+currentNum);

            if(currentNum == 0){
                  uiDisable($firstButton);
                  uiDisable($previousButton);
            }else{
                  uiEnable($firstButton);
                  uiEnable($previousButton);
            }
      
            if(currentNum == imagesNum - 1){
                  uiDisable($nextButton);
                  uiDisable($lastButton);
            }else{
                  uiEnable($nextButton);
                  uiEnable($lastButton);
            }
      
      let img = new Image();
      img.onload = function() {      
            let dimensions = getDimensions(this.width,this.height,maxWidth,maxHeight)
            // console.log(`${this.width}x${this.height}`);
            // console.log(`${width}x${height}`);
            $mainImage.css('width',dimensions.width);
            $mainImage.css('height',dimensions.height);
            
            $mainImage.attr('src', this.src);
            $mainImage.fadeIn(1000);
      }
      setTimeout(()=>img.src = `${imgPath}${imagesArray[currentNum]}`,1000);
      // console.log(`Source: ${img.src}: ${img.w}x${img.h}`);
      
}

function initSelectors(n){

      if($('.image-selectors').children().length)
            $('.image-selectors').children().remove();
      
      for (let i = 0; i < imagesNum; i++) {
            $('.image-selectors').append(`<i class='fa-circle indicator ${i==n ? 'fas active' : 'far'}' id='img_${i}' title='${imagesArray[i]}'></i>`);      
      }
}

function showMaxImage(n){

      // console.log(`old image is: ${$mainImage.attr('src')}`);
      if(n < 0 || n > imagesNum-1)
            return;
      if(n == 0)
            $('#max-previous').css('display','none');
      else
            $('#max-previous').css('display','block');
      
      if(n == imagesNum - 1)
            $('#max-next').css('display','none');
      else
            $('#max-next').css('display','block');                  
      
      currentNum = n;

      let img = new Image();
      img.onload = function() {      
            let dimensions = getDimensions(this.width,this.height,$(window).width(),$(window).height());
            
            $maxImage.css('width',dimensions.width);
            $maxImage.css('height',dimensions.height);            
            $maxImage.css('align-items','center');            
            $maxImage.attr('src', this.src);
      }

      img.src = `${imgPath}${imagesArray[currentNum]}`;
}

function uiEnable($el){
      $el.css('color',colorUiEnabled);
      $el.css('cursor','pointer');
}

function uiDisable($el){
      $el.css('color',colorUiDisabled);
      $el.css('cursor','none');
}

function getDimensions(curW,curH,trgW,trgH){
      
      let ratio = curW/curH;
      let result = {
            'width':0,
            'height':0
      };
      if(curH >= curW && curH > trgH){
            result.height = trgH;
            result.width = Math.floor(trgH/curH*curW);
      }else{
            result.width = trgW;
            result.height = Math.floor(trgW/curW*curH);
      }
      
      if(ratio < trgW/trgH && result.height > trgH){
            result.width = Math.floor(trgH/result.height*result.width);
            result.height = trgH;
      }

      return result;
}