# -*- coding: utf-8 -*-
"""
@author: Prabhav Mishra 
"""


def snake(timeLimit) : 
    import turtle , random , time 
    count = 0 
    delay = 0.09
    score = 0
    level=1
    #window 
    win = turtle.Screen() 
    win.title("__Snake__")
    win.bgcolor("black")
    win.setup(width=800,height=600)
    win.tracer(0)
    
    #snake 
    snake = turtle.Turtle() 
    snake.speed(0) # animation speed to max 
    snake.shape("square")
    snake_colors=["White","Red","Brown","Orange","White","Light Blue","Green","Blue","Yellow","Purple","Cyan"]
    snake.color("white") 
    snake.shapesize(stretch_wid = 1 , stretch_len = 1)
    snake.penup() #avoid lines while moving 
    snake.direction = "stop" # initial direction 
    
     
    #snake body array 
    snake_body = [] 
     
    #bait of the snake  
    food = turtle.Turtle() 
    food.speed(0) #animation speed max 
    food.shape("circle")
    food.color("red")
    food.penup()  #avoid lines 
    food.goto(random.randint(-340,+340) ,random.randint(-280 , +280 ))
    
    #score[pen]
    pen = turtle.Turtle() 
    pen.speed(0)
    pen.color('white')
    pen.penup() 
    pen.hideturtle() 
    pen.goto(0,+280)
    pen.write("Score :   "  , align = "center" , font = ("Arial" , 14 , "bold" ))
    
    #level[lpen]
    lpen= turtle.Turtle()
    lpen.speed(0)
    lpen.color("white")
    lpen.penup()
    lpen.hideturtle()
    lpen.goto(0,+260)
    lpen.write("Level :  " ,align = "center" , font = ("Arial" , 14 , "bold"))
    
    #time[pen]
    tpen = turtle.Turtle()
    tpen.speed(0)
    tpen.color('white')
    tpen.penup() 
    tpen.hideturtle() 
    tpen.goto(+300,+280)
    tpen.write("Time left :   "  , align = "center" , font = ("Arial" , 14 , "bold" ))
    
    #movement of the snake 
    def move() : 
        if snake.direction =="up" : 
            snake.sety(snake.ycor() + 20)
        if snake.direction == "down" : 
            snake.sety(snake.ycor() - 20 )
        if snake.direction == "left" : 
            snake.setx(snake.xcor() - 20 )
        if snake.direction == "right" : 
            snake.setx(snake.xcor() + 20 )
    
    #snake's head direction it's pointing 
    def  up() : 
        if snake.direction != "down":
            snake.direction = "up"
    def down() :
        if snake.direction != "up":
            snake.direction = "down"
    def  right() : 
        snake.direction = "right"
    def  left() : 
        snake.direction = "left"
     
    
    #key intput 
    win.listen()
    win.onkeypress(up, "w")
    win.onkeypress(down, "s")
    win.onkeypress(left, "a")
    win.onkeypress(right, "d")
       
    startTime = time.time()
    elapsedTime = 0
    while True : 
        win.update()
        
        if (timeLimit>=elapsedTime):
            timex = timeLimit-elapsedTime 
            tpen.clear() 
            tpen.write("Time Left : {}  ".format(timex), align = "center" , font = ("Arial" , 14 , "bold" ))
      
        if snake.distance(food) <25:
           
            # move the food to a random position on screen
            x = random.randint(-290, 290)
            y = random.randint(-290, 290)
            food.goto(x, y)
            
            #Display : Score + Updating it 
            score = score + 1 
            
            if score%10==0:
                level=score//10+1
                bg_colors=["Black","Cyan","Purple","Yellow","Blue", "Green","Light Blue","White","Orange","Brown","Red"]
                win.bgcolor(bg_colors[level-1])
                snake_colors=["White","Red","Brown","Orange","White","Light Blue","Green","Blue","Yellow","Purple","Cyan"]
                snake.color(snake_colors[level-1])
                
            pen.clear() 
            lpen.clear()
            pen.write("Score : {}  ".format(score), align = "center" , font = ("Arial" , 14 , "bold" ))
            lpen.write("Level : {} ".format(level) ,align = "center" , font = ("Arial" , 14 , "bold"))
            # add a segment to the body of the snake 
            body = turtle.Turtle()
            body.speed(0)
            body.shape("square")
            body.color(snake_colors[level-1])
            body.penup()
            snake_body.append(body)
       
    
            # Move the end segments first in reverse order
        for index in range(len(snake_body)-1, 0 , -1):
            x = snake_body[index-1].xcor()
            y = snake_body[index-1].ycor()
            snake_body[index].goto(x, y)
    
            #fixing segment 0 
        if len(snake_body) > 0:
            x = snake.xcor()
            y = snake.ycor()
            snake_body[0].goto(x,y)
       
        #decreasing the speed of the animation 
        time.sleep(delay)  
       
        # in order to keep moving the snake     
        move() 
           
        #collision time border 
        if snake.xcor() > 390 : 
           snake.goto(snake.xcor() * (-1) , snake.ycor())
        elif snake.xcor() < -390 :  
           snake.goto(snake.xcor()*(-1) , snake.ycor())
        elif snake.ycor() > 290 : 
           snake.goto(snake.xcor(),snake.ycor()*(-1))
        elif snake.ycor() < - 290 : 
            snake.goto(snake.xcor() , snake.ycor() * (-1))
    
    
        #collision against snake body 
        for body in snake_body :
           if body.distance(snake) < 20 : 
              time.sleep(1)
              snake.goto(0,0) 
              snake.direction = "stop" 
           	#hide the body part of the snake 
              for body in snake_body : 
                  body.goto(1000,1000) 
              #clear the list 
              snake_body.clear()
              #refresh the score 
              pen.clear() 
              lpen.clear()
              pen.write("Score :   "  , align = "center" , font = ("Arial" , 14 , "bold" ))
              lpen.write("Level :  " ,align = "center" , font = ("Arial" , 14 , "bold"))                      
              score = 0
        
        # Timer
        elapsedTime = int(time.time() - startTime)
        if elapsedTime>timeLimit:
            snake.goto(0,0) 
            snake.direction = "stop"
            snake.color("black")
            food.color("black")
            for body in snake_body : 
                  body.goto(1000,1000) 
              #clear the list 
            snake_body.clear()
            tpen.clear()
            pen.clear()
            pen.write("Game Over"  , align = "center" , font = ("Arial" , 14 , "bold" ))
          
        
        
timeLimit = 10
snake(timeLimit)         
    
        
