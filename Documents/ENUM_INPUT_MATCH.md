# Enum 값과 입력된 값 대조하기

## 입력된 값과 enum 대조
* dto에 enum을 쓰는 방법도 있지만, enum안에서 enum 값과 입력값을 대조하는 방법도 있다.
* 이 문서에서는 enum값과 입력된 값을 대조하는 것을 설명한다.
* 특히나 문자열을 위주로 설명한다. 더욱이 혹시모를 상황에 대비해 대소문자를 구분하지 않고 문자열을 받는 방법을 소개한다.

## dto
* dto에는 문자열을 입력받는다.
* 후에 enum의 static 메서드를 통해서 값을 대조해서,
* 문자열을 적절한 enum으로 대체시켜 리턴하고,
* 문제가 생길시 customException을 던진다.
* 앞서 말했듯이 대소문자를 구분하지 않고 넣어도 되나,
* 웬만하면 enum의 값과 통일하여 값을 삽입하는 것이 좋다.

## 코드
* enum에서 values()는 enum타입의 모든 값을 배열로 만들어 리턴한다.
* 이에 반해 name은 enum의 값을 문자열로 리턴한다.
* for-each 문을 이용해 하나씩 돌려가며 enum을 대조한다.
* enum의 값과 문자열을 대소문자를 구분하지 않고 비교한다.
* 값이 맞다면 enum값을 리턴하고, 어떠한 값도 없다면 custom exception을 던진다.
```
public enum TaxiGrade {
    NORMAL, PREMIUM, VAN;

    public static TaxiGrade matchGrade(String grade) {
        for (TaxiGrade taxiGrade : TaxiGrade.values()) {
            if (taxiGrade.name().equalsIgnoreCase(grade)) {
                return taxiGrade;
            }
        }
        throw new TaxiCustomException(ResponseMessage.NOT_EXIST_GRADE);
    }
}
```

## enum 리턴시 주의할 점
* response dto 등으로 값을 리턴할 때에는 enum 상수만 저장되어있는 경우에는 그냥 enum 타입으로 전달하면된다.
* 그러나 enum 상수안에 값이 들어있는 형태라면, 들어있는 값을 꺼내어 리턴해주어야한다.
* 그냥 enum 타입을 리턴하면 enum.name()의 형태로 출력된다.
* getter와 생성자, 그리고 적절한 타입의 필드를 추가하여 get필드()를 사용하게 되면 원하는 형태로 값을 꺼낼 수 있다.