import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../services/api";
import { useAuth } from "../context/AuthContext";
export default function ProductDetails() {
  const { id } = useParams(),
    [p, setP] = useState(),
    [q, setQ] = useState(1),
    { user } = useAuth();
  useEffect(() => {
    api.get("/products/" + id).then((r) => setP(r.data));
  }, [id]);
  if (!p) return <main>Loading...</main>;
  async function add() {
    if (!user) return alert("Please login first.");
    await api.post("/cart/items", { productId: p.id, quantity: q });
    alert("Added to cart");
  }
  return (
    <main>
      <div className="detail">
        <img src={p.imageUrl} />
        <div>
          <span>{p.category}</span>
          <h1>{p.name}</h1>
          <p>{p.description}</p>
          <h2>₹{p.price.toLocaleString("en-IN")}</h2>
          <p>{p.stock} items available</p>
          <div className="qty">
            <input
              type="number"
              min="1"
              max={p.stock}
              value={q}
              onChange={(e) => setQ(+e.target.value)}
            />
            <button className="primary" onClick={add}>
              Add to cart
            </button>
          </div>
        </div>
      </div>
    </main>
  );
}
