import Footer from "./Customer/Components/Footer/footer";
import Navigation from "./Customer/Components/Navigation/navigation";
// import Product from "./Customer/Components/Product/Product";
// import ProductDetails from "./Customer/Components/ProductDetails/ProductDetails";
// import { Homepage } from "./Customer/Pages/Homepage";
// import Cart from "./Customer/Components/Cart/Cart";
import CheckOut from "./Customer/Components/CheckOut/CheckOut";
const App = () => {
  return (
    <div>
      <Navigation />
      <div>
        <CheckOut />
        {/* <Cart />
         */}
        {/* <ProductDetails /> */}
        {/* <Product />
        <Homepage /> */}
      </div>
      <Footer />
    </div>
  );
};

export default App;
