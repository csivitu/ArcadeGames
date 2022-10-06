var order = [];
var ind = 0 ;

function addNewTile()
{
  var tile = Math.floor(Math.random()*4);

  if(tile == 0)
  {
    $("#g").addClass("pressed");
    new Audio("AUDIO/green.mp3").play();
    setTimeout(function(){
      $("#g").removeClass("pressed");
    },50);
  }
  else if(tile == 1)
  {
    $("#r").addClass("pressed");
    new Audio("AUDIO/red.mp3").play();
    setTimeout(function(){
      $("#r").removeClass("pressed");
    },50);
  }
  else if(tile == 2)
  {
    $("#y").addClass("pressed");
    new Audio("AUDIO/yellow.mp3").play();
    setTimeout(function(){
      $("#y").removeClass("pressed");
    },50);
  }
  else if(tile == 3)
  {
    $("#b").addClass("pressed");
    new Audio("AUDIO/blue.mp3").play();
    setTimeout(function(){
      $("#b").removeClass("pressed");
    },50);
  }

  order.push(tile);
  ind = 0;
}

function gameOver()
{
  $("body").addClass("game-over");
  new Audio("AUDIO/wrong.mp3").play();
  setTimeout(function(){
    $("body").removeClass("game-over");
  },50);
  setTimeout(function(){
    $(".board").css("display","none");
    $(".restart-menu").css("display","block");
  },100);
  order = [];
  ind = 0;
}

$("#g").click(function(){
  if(order[ind] == 0)
  {
    $("#g").addClass("pressed");
    new Audio("AUDIO/green.mp3").play();
    setTimeout(function(){
      $("#g").removeClass("pressed");
    },50);

    ind += 1;

    if(ind == order.length)
    {
      setTimeout(function(){
        addNewTile();
      },750);
    }
  }
  else {
    gameOver();
  }
});

$("#r").click(function(){
  if(order[ind] == 1)
  {
    $("#r").addClass("pressed");
    new Audio("AUDIO/red.mp3").play();
    setTimeout(function(){
      $("#r").removeClass("pressed");
    },50);

    ind += 1;

    if(ind == order.length)
    {
      setTimeout(function(){
        addNewTile();
      },750);
    }
  }
  else {
    gameOver();
  }
});

$("#y").click(function(){
  if(order[ind] == 2)
  {
    $("#y").addClass("pressed");
    new Audio("AUDIO/yellow.mp3").play();
    setTimeout(function(){
      $("#y").removeClass("pressed");
    },50);

    ind += 1;

    if(ind == order.length)
    {
      setTimeout(function(){
        addNewTile();
      },750);
    }
  }
  else {
    gameOver();
  }
});

$("#b").click(function(){
  if(order[ind] == 3)
  {
    $("#b").addClass("pressed");
    new Audio("AUDIO/blue.mp3").play();
    setTimeout(function(){
      $("#b").removeClass("pressed");
    },50);

    ind += 1;

    if(ind == order.length)
    {
      setTimeout(function(){
        addNewTile();
      },750);
    }
  }
  else {
    gameOver();
  }
});

$("#rules-button").click(function(){
  window.location.href = "https://en.wikipedia.org/wiki/Simon_(game)";
});

$("#play-button").click(function(){

  $(".main-menu").css("display","none");
  $(".board").css("display","block");

  setTimeout(addNewTile,1000);
});

$("#restart-button").click(function(){

  $(".restart-menu").css("display","none");
  $(".board").css("display","block");

  setTimeout(addNewTile,1000);
});

$("#exit-button").click(function(){
  $(".restart-menu").css("display","none");
  $(".main-menu").css("display","block");
});
