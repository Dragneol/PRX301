var myIndex = -1;
var flag = false;
var itemNumber = -1;
var timeClose;
var items = document.getElementsByClassName("item-slide");

slideShow();
addButtonEven();
function slideShow(){
    var buttons = document.getElementsByClassName("item-button");
    myIndex++;
    if(flag == true){
        myIndex = itemNumber;
        flag = false;
    }
    myIndex %= 3;

    for(var i = 0; i < buttons.length; i++){
        buttons[i].className =  "item-button";
    }
    buttons[myIndex].className += " selection";
    showItemSlide(myIndex);
    setTimeout(hiddenItemSlide, 4000);
}

function showItemSlide(itemNumber){
    items[itemNumber].children[1].className = 'slide-cover1';
    items[itemNumber].children[2].className = 'slide-cover2';
    items[itemNumber].children[3].className = 'slide-cover3';
    items[myIndex].children[4].className = 'content';
    items[itemNumber].style.display = "block";
}

function hiddenItemSlide(){
    items[myIndex].children[2].className = 'cover2';
    items[myIndex].children[1].className = 'cover1';
    items[myIndex].children[3].className = 'cover3';
    items[myIndex].children[4].className += ' animation-content';
    delaySlideShow();
}

function delaySlideShow(){
    timeClose = 1000;
    if(flag == true){
        timeClose = 400;
    }
    setTimeout(function(){
        items[myIndex].style.display = "none";
        slideShow();
    }, timeClose);
}

function addButtonEven(){
    var buttons = document.getElementsByClassName("item-button");
    for(var i = 0; i < buttons.length; i++){
        buttons[i].addEventListener("click", changeSlide);
    }
}

function changeSlide(event){
    itemNumber = event.target.getAttribute("value");
    flag = true;
}