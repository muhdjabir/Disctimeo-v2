import Link from "next/link"
import { Button } from "../ui/button"

const Hero = () => {
    return (
        <div className="w-full bg-white py-16 px-4">
            <div className="max-w-[1240px] mx-auto grid md:grid-cols-2">
                <div className="flex flex-col text-black justify-between">
                    <h1 className="text-2xl sm:text-3xl md:text-4xl py-2 text-left font-bold border-b-8 border-sky-700">
                        Connect and Elevate your Disc Time
                    </h1>
                    <p className="text-xl sm:text-2xl text-grey font-normal text-left">
                        The best place to connect and stay in the loop with the
                        goings of the Frisbee Community
                    </p>
                    <div className="flex flex-row gap-10">
                        <Button className="bg-sky-700 w-[200px] font-medium my-6 mx-auto md:mx-0 py-3">
                            <a href="/api/auth/login">Join the Community</a>
                        </Button>
                        <Button className="w-[200px] font-medium my-6 mx-auto md:mx-0 py-3 underline" variant={"link"}>
                            <Link href="/events">
                                Explore Events near you
                            </Link>
                        </Button>
                    </div>
                </div>
                <div>
                    <img src="/laptop.jpg"
                        className="w-[500px] mx-auto my-4"
                    />
                </div>
            </div>
        </div>
    )
}

export default Hero;