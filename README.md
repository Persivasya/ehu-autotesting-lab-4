# Lab 4 – Triangle

Enter three positive integers (a, b, c). The app checks whether they form a triangle and classifies it by length and by angle.

Run: `mvn -q package && java -cp target/classes com.example.Main`

Tests: `mvn test`

## Domain testing

### Input validation

| Class | Values |
|---|---|
| Valid natural numbers | `3 4 5`, `1 1 999999999` |
| Zero | `0` |
| Negative | `-1` |
| Letters | `abc`, `x` |
| Decimal | `1.5` |
| Exponent / sign | `1e3`, `+5` |
| Empty / blank | `""`, `" "` |
| Too large | `1000000000`, `99999999999999999999` |
| Null | `null` |
| Missing values | `3 4`, `3` |

### Triangle existence

| Class | Values (a, b, c) |
|---|---|
| Triangle | `3,4,5` `2,2,3` `1,1,1` `5,4,3` `3,5,4` `999999999,999999999,999999999` |
| Degenerate (a+b = c) | `1,2,3` `3,2,1` `1,3,2` |
| Not a triangle (a+b < c) | `1,1,3` `10,2,3` `2,10,3` `2,3,10` |

### By length

| Class | Values (a, b, c) |
|---|---|
| Equilateral | `3,3,3` `1,1,1` |
| Isosceles | `3,3,4` `3,4,3` `4,3,3` |
| Scalene | `3,4,5` `5,4,3` |

### By angle

| Class | Values (a, b, c) |
|---|---|
| Right-angled (c² = a²+b²) | `3,4,5` `5,3,4` `4,5,3` `5,12,13` |
| Acute-angled (c² < a²+b²) | `3,3,3` `4,5,6` `6,5,4` `5,6,4` `4,4,5` |
| Obtuse-angled (c² > a²+b²) | `2,2,3` `2,3,4` `4,3,2` `3,4,2` `999999999,999999999,1999999997` |

### Main (console)

| Input | Output |
|---|---|
| `3 4 5` | SCALENE, RIGHT_ANGLED |
| `4 4 5` | ISOSCELES, ACUTE_ANGLED |
| `3 3 3` | EQUILATERAL, ACUTE_ANGLED |
| `2 3 4` | SCALENE, OBTUSE_ANGLED |
| `1 2 3`, `1 1 5` | Not a triangle |
| `a b c`, `0 4 5`, `-3 4 5`, `3 4`, `1.5 2 3` | Invalid input |
