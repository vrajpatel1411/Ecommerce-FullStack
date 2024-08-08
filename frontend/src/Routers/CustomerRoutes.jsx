import { Route, Routes } from "react-router-dom";
import { Homepage } from "../Customer/Pages/Homepage";
import Cart from "../Customer/Components/Cart/Cart";
import Order from "../Customer/Components/Order/Order";
import OrderDetails from "../Customer/Components/Order/OrderDetails";
import CheckOut from "../Customer/Components/CheckOut/CheckOut";
import Navigation from "../Customer/Components/Navigation/navigation";
import Footer from "../Customer/Components/Footer/footer";
import Product from "../Customer/Components/Product/Product";
import ProductDetails from "../Customer/Components/ProductDetails/ProductDetails";
const CustomerRoutes = () => {
  return (
    <div>
      <div>
        <Navigation />
      </div>
      <Routes>
        <Route
          path="/"
          element={<Homepage />}></Route>

        <Route
          path="/cart"
          element={<Cart />}></Route>

        <Route
          path="/:levelOne/:levelTwo/:levelThree"
          element={<Product />}></Route>

        <Route
          path="/product/:productId"
          element={<ProductDetails />}></Route>

        <Route
          path="/checkout"
          element={<CheckOut />}></Route>

        <Route
          path="/account/orders"
          element={<Order />}></Route>

        <Route
          path="/account/order/:orderId"
          element={<OrderDetails />}></Route>
      </Routes>

      <div>
        <Footer />
      </div>
    </div>
  );
};

export default CustomerRoutes;
