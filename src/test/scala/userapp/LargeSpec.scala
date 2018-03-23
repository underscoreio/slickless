package userapp

/* Ensure slickless compiles for large case classes with a nice set of imports. */

import slick.jdbc.H2Profile.api._
import shapeless._
import slickless._

case class Large(
  a: Int, b: Int, c: Int, d: Int,
  e: Int, f: Int, g: Int, h: Int,
  i: Int, j: Int, k: Int, l: Int,
  m: Int, n: Int, o: Int, p: Int,
  q: Int, r: Int, s: Int, t: Int,
  u: Int, v: Int, w: Int, x: Int,
  y: Int, z: Int
)

class LargeTable(tag: Tag) extends Table[Large](tag, "large") {
  def a = column[Int]("a")
  def b = column[Int]("b")
  def c = column[Int]("c")
  def d = column[Int]("d")
  def e = column[Int]("e")
  def f = column[Int]("f")
  def g = column[Int]("g")
  def h = column[Int]("h")
  def i = column[Int]("i")
  def j = column[Int]("j")
  def k = column[Int]("k")
  def l = column[Int]("l")
  def m = column[Int]("m")
  def n = column[Int]("n")
  def o = column[Int]("o")
  def p = column[Int]("p")
  def q = column[Int]("q")
  def r = column[Int]("r")
  def s = column[Int]("s")
  def t = column[Int]("t")
  def u = column[Int]("u")
  def v = column[Int]("v")
  def w = column[Int]("w")
  def x = column[Int]("x")
  def y = column[Int]("y")
  def z = column[Int]("z")

  def * = (
    a :: b :: c :: d ::
    e :: f :: g :: h ::
    i :: j :: k :: l ::
    m :: n :: o :: p ::
    q :: r :: s :: t ::
    u :: v :: w :: x ::
    y :: z :: HNil
  ).mappedWith(Generic[Large])
}

import scala.concurrent.ExecutionContext.Implicits.global

class LargeSpec extends slickless.Spec {

  "slick tables with >22 column mappings" - {

    "should support inserts and selects" in {
      val LargeTable = TableQuery[LargeTable]

      val db = Database.forConfig("h2")

      val large = Large(
         1,  2,  3,  4, 
         5,  6,  7,  8, 
         9, 10, 11, 12, 
        13, 14, 15, 16, 
        17, 18, 19, 20, 
        21, 22, 23, 24, 
        25, 26
      )

      val action = for {
        _   <- LargeTable.schema.create
        _   <- LargeTable += large
        ans <- LargeTable.result.head
        _   <- LargeTable.schema.drop
      } yield ans

      whenReady(db.run(action)) { _ should equal(large) }
    }

    "very large case classes should compile" in {

      // Requires increase in -Xss
      case class Large144(
        x001: Int, x002: Int, x003: Int, x004: Int, x005: Int, x006: Int, x007: Int, x008: Int, x009: Int,
        x010: Int, x011: Int, x012: Int, x013: Int, x014: Int, x015: Int, x016: Int, x017: Int, x018: Int, x019: Int,
        x020: Int, x021: Int, x022: Int, x023: Int, x024: Int, x025: Int, x026: Int, x027: Int, x028: Int, x029: Int,
        x030: Int, x031: Int, x032: Int, x033: Int, x034: Int, x035: Int, x036: Int, x037: Int, x038: Int, x039: Int,
        x040: Int, x041: Int, x042: Int, x043: Int, x044: Int, x045: Int, x046: Int, x047: Int, x048: Int, x049: Int,
        x050: Int, x051: Int, x052: Int, x053: Int, x054: Int, x055: Int, x056: Int, x057: Int, x058: Int, x059: Int,
        x060: Int, x061: Int, x062: Int, x063: Int, x064: Int, x065: Int, x066: Int, x067: Int, x068: Int, x069: Int,
        x070: Int, x071: Int, x072: Int, x073: Int, x074: Int, x075: Int, x076: Int, x077: Int, x078: Int, x079: Int,
        x080: Int, x081: Int, x082: Int, x083: Int, x084: Int, x085: Int, x086: Int, x087: Int, x088: Int, x089: Int,
        x090: Int, x091: Int, x092: Int, x093: Int, x094: Int, x095: Int, x096: Int, x097: Int, x098: Int, x099: Int,
        x100: Int, x101: Int, x102: Int, x103: Int, x104: Int, x105: Int, x106: Int, x107: Int, x108: Int, x109: Int,
        x110: Int, x111: Int, x112: Int, x113: Int, x114: Int, x115: Int, x116: Int, x117: Int, x118: Int, x119: Int,
        x120: Int, x121: Int, x122: Int, x123: Int, x124: Int, x125: Int, x126: Int, x127: Int, x128: Int, x129: Int,
        x130: Int, x131: Int, x132: Int, x133: Int, x134: Int, x135: Int, x136: Int, x137: Int, x138: Int, x139: Int,
        x140: Int, x141: Int, x142: Int, x143: Int, x144: Int
      )

     val genericLarge144 = Generic[Large144]

     
     class Large144Table(tag: Tag) extends Table[Large144](tag, "large144") {
        def a001 = column[Int]("a001")
        def a002 = column[Int]("a002")
        def a003 = column[Int]("a003")
        def a004 = column[Int]("a004")
        def a005 = column[Int]("a005")
        def a006 = column[Int]("a006")
        def a007 = column[Int]("a007")
        def a008 = column[Int]("a008")
        def a009 = column[Int]("a009")
        def a010 = column[Int]("a010")
        def a011 = column[Int]("a011")
        def a012 = column[Int]("a012")
        def a013 = column[Int]("a013")
        def a014 = column[Int]("a014")
        def a015 = column[Int]("a015")
        def a016 = column[Int]("a016")
        def a017 = column[Int]("a017")
        def a018 = column[Int]("a018")
        def a019 = column[Int]("a019")
        def a020 = column[Int]("a020")
        def a021 = column[Int]("a021")
        def a022 = column[Int]("a022")
        def a023 = column[Int]("a023")
        def a024 = column[Int]("a024")
        def a025 = column[Int]("a025")
        def a026 = column[Int]("a026")
        def a027 = column[Int]("a027")
        def a028 = column[Int]("a028")
        def a029 = column[Int]("a029")
        def a030 = column[Int]("a030")
        def a031 = column[Int]("a031")
        def a032 = column[Int]("a032")
        def a033 = column[Int]("a033")
        def a034 = column[Int]("a034")
        def a035 = column[Int]("a035")
        def a036 = column[Int]("a036")
        def a037 = column[Int]("a037")
        def a038 = column[Int]("a038")
        def a039 = column[Int]("a039")
        def a040 = column[Int]("a040")
        def a041 = column[Int]("a041")
        def a042 = column[Int]("a042")
        def a043 = column[Int]("a043")
        def a044 = column[Int]("a044")
        def a045 = column[Int]("a045")
        def a046 = column[Int]("a046")
        def a047 = column[Int]("a047")
        def a048 = column[Int]("a048")
        def a049 = column[Int]("a049")
        def a050 = column[Int]("a050")
        def a051 = column[Int]("a051")
        def a052 = column[Int]("a052")
        def a053 = column[Int]("a053")
        def a054 = column[Int]("a054")
        def a055 = column[Int]("a055")
        def a056 = column[Int]("a056")
        def a057 = column[Int]("a057")
        def a058 = column[Int]("a058")
        def a059 = column[Int]("a059")
        def a060 = column[Int]("a060")
        def a061 = column[Int]("a061")
        def a062 = column[Int]("a062")
        def a063 = column[Int]("a063")
        def a064 = column[Int]("a064")
        def a065 = column[Int]("a065")
        def a066 = column[Int]("a066")
        def a067 = column[Int]("a067")
        def a068 = column[Int]("a068")
        def a069 = column[Int]("a069")
        def a070 = column[Int]("a070")
        def a071 = column[Int]("a071")
        def a072 = column[Int]("a072")
        def a073 = column[Int]("a073")
        def a074 = column[Int]("a074")
        def a075 = column[Int]("a075")
        def a076 = column[Int]("a076")
        def a077 = column[Int]("a077")
        def a078 = column[Int]("a078")
        def a079 = column[Int]("a079")
        def a080 = column[Int]("a080")
        def a081 = column[Int]("a081")
        def a082 = column[Int]("a082")
        def a083 = column[Int]("a083")
        def a084 = column[Int]("a084")
        def a085 = column[Int]("a085")
        def a086 = column[Int]("a086")
        def a087 = column[Int]("a087")
        def a088 = column[Int]("a088")
        def a089 = column[Int]("a089")
        def a090 = column[Int]("a090")
        def a091 = column[Int]("a091")
        def a092 = column[Int]("a092")
        def a093 = column[Int]("a093")
        def a094 = column[Int]("a094")
        def a095 = column[Int]("a095")
        def a096 = column[Int]("a096")
        def a097 = column[Int]("a097")
        def a098 = column[Int]("a098")
        def a099 = column[Int]("a099")
        def a100 = column[Int]("a100")
        def a101 = column[Int]("a101")
        def a102 = column[Int]("a102")
        def a103 = column[Int]("a103")
        def a104 = column[Int]("a104")
        def a105 = column[Int]("a105")
        def a106 = column[Int]("a106")
        def a107 = column[Int]("a107")
        def a108 = column[Int]("a108")
        def a109 = column[Int]("a109")
        def a110 = column[Int]("a110")
        def a111 = column[Int]("a111")
        def a112 = column[Int]("a112")
        def a113 = column[Int]("a113")
        def a114 = column[Int]("a114")
        def a115 = column[Int]("a115")
        def a116 = column[Int]("a116")
        def a117 = column[Int]("a117")
        def a118 = column[Int]("a118")
        def a119 = column[Int]("a119")
        def a120 = column[Int]("a120")
        def a121 = column[Int]("a121")
        def a122 = column[Int]("a122")
        def a123 = column[Int]("a123")
        def a124 = column[Int]("a124")
        def a125 = column[Int]("a125")
        def a126 = column[Int]("a126")
        def a127 = column[Int]("a127")
        def a128 = column[Int]("a128")
        def a129 = column[Int]("a129")
        def a130 = column[Int]("a130")
        def a131 = column[Int]("a131")
        def a132 = column[Int]("a132")
        def a133 = column[Int]("a133")
        def a134 = column[Int]("a134")
        def a135 = column[Int]("a135")
        def a136 = column[Int]("a136")
        def a137 = column[Int]("a137")
        def a138 = column[Int]("a138")
        def a139 = column[Int]("a139")
        def a140 = column[Int]("a140")
        def a141 = column[Int]("a141")
        def a142 = column[Int]("a142")
        def a143 = column[Int]("a143")
        def a144 = column[Int]("a144")

        def * = (
          a001 :: a002 :: a003 :: a004 :: a005 :: a006 :: a007 :: a008 :: a009 ::
          a010 :: a011 :: a012 :: a013 :: a014 :: a015 :: a016 :: a017 :: a018 :: a019 ::
          a020 :: a021 :: a022 :: a023 :: a024 :: a025 :: a026 :: a027 :: a028 :: a029 ::
          a030 :: a031 :: a032 :: a033 :: a034 :: a035 :: a036 :: a037 :: a038 :: a039 ::
          a040 :: a041 :: a042 :: a043 :: a044 :: a045 :: a046 :: a047 :: a048 :: a049 ::
          a050 :: a051 :: a052 :: a053 :: a054 :: a055 :: a056 :: a057 :: a058 :: a059 ::
          a060 :: a061 :: a062 :: a063 :: a064 :: a065 :: a066 :: a067 :: a068 :: a069 ::
          a070 :: a071 :: a072 :: a073 :: a074 :: a075 :: a076 :: a077 :: a078 :: a079 ::
          a080 :: a081 :: a082 :: a083 :: a084 :: a085 :: a086 :: a087 :: a088 :: a089 ::
          a090 :: a091 :: a092 :: a093 :: a094 :: a095 :: a096 :: a097 :: a098 :: a099 ::
          a100 :: a101 :: a102 :: a103 :: a104 :: a105 :: a106 :: a107 :: a108 :: a109 ::
          a110 :: a111 :: a112 :: a113 :: a114 :: a115 :: a116 :: a117 :: a118 :: a119 ::
          a120 :: a121 :: a122 :: a123 :: a124 :: a125 :: a126 :: a127 :: a128 :: a129 ::
          a130 :: a131 :: a132 :: a133 :: a134 :: a135 :: a136 :: a137 :: a138 :: a139 ::
          a140 :: a141 :: a142 :: a143 :: a144 :: HNil
        ).mappedWith(genericLarge144)

      }

    }
  }
}
