// app/page.tsx

"use client";
import Link from "next/link";
import { useUser } from "@auth0/nextjs-auth0/client";
import Hero from "@/components/landing/Hero";

const Landing = () => {
  const { user, isLoading, error } = useUser();

  if (isLoading) <div className="text-center mt-10">Loading...</div>;
  if (error) <div className="text-center mt-10">{error.message}</div>;

  return (
    <div className="body-font text-center content-center">
      <Hero />
    </div>
  );
};

export default Landing;
