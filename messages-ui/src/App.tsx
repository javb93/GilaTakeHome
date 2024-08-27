import React from "react";
import logo from "./logo.svg";
import "./App.css";
import NavBar from "./layouts/nav-bar";
import { Route, Routes } from "react-router-dom";
import Messages from "./layouts/messages";
import History from "./layouts/history";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
      <NavBar />
      <Routes>
        <Route path="/messages" element={<Messages />} />

        <Route path="/log" element={<History />} />
      </Routes>
    </QueryClientProvider>
  );
}

export default App;
