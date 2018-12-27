I will use on slip day
In the composite paint object use another constructor.
For get velocity, i didnot initialize the velocity.
Didn't initialize the radius, location.
setVelocity,getVelocity,getrotationangle,getvroate,setangle,rotate,collision,getLocation,setLocation,getColor,setColor,setRadius,nextLocation are not applicable to composite object.

The getStrategy method is ok because I set the father sharing the same strategy kind with the children. They are not the same strategy object, just the same kind. This strategy is not used, just the name
of strategy is used.

This composite design pattern didn't traverse all the children, it just transerfer the cmd in the update method.
