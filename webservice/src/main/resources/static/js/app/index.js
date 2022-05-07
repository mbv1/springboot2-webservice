var main = {
    init : function () {
        const _this = this;
        $('#btn-save').click(() => {
            _this.save();
        });

        $('#btn-update').click(() => {
            _this.update();
        });

        $('#btn-delete').click(() => {
            _this.delete();
        });

        $('#search-posts').click(() => {
            _this.search();
        });
    },
    save : function(){
        const data = {
            title : $('#title').val(),
            content : $('#content').val(),
            author : $('#author').val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data),
            success : function(){
                alert('글이 등록되었습니다.');
                window.location.href = '/';
            },
            error : function(error){
            alert(JSON.stringify(error));
            }
        });
    },

    update : function(){
        const data = {
        title : $('#title').val(),
        content : $('#content').val()
        };

        const id = $('#id').val();
        $.ajax({
            type : 'PUT',
            url : '/api/v1/posts/' + id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data),
            success : function(){
                alert('글이 수정되었습니다.');
                window.location.href = '/';
            },
            error : function(error){
            alert(JSON.stringify(error));
            }
        });
    },
    delete : function(){

        const id = $('#id').val();

        $.ajax({
            type : 'DELETE',
            url : '/api/v1/posts/' + id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            success : function(){
                alert('글이 삭제되었습니다.');
                window.location.href = '/';
            },
            error : function(error){
            alert(JSON.stringify(error));
            }
        });
    },

    search : function(){
        const selected = $("#option-box option:selected").val();
        const content = $("#keyword").val();
        if(content === ''){
          alert('검색어를 입력하세요')
          return;
        }
        window.location.href = '/?' + selected + '=' + content

    }
};

main.init();