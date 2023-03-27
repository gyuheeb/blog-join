let index = {
    init: founction(){
        $('#btn-save').onclick(() =>{
            this.save();
        });
    },
    save: function(){
        lat date ={
            username: ${"#username"}.val(),
            email: ${"#email"}.val(),
            password: ${"#password"}.val()
            
        }
     
    }
}

index.init();